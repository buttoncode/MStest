package com.buttoncode.mstest.admin.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.buttoncode.mstest.core.MStestException;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.admin.web.utils.MessageCodes;
import com.buttoncode.mstest.admin.web.utils.WebUtils;
import com.buttoncode.mstest.admin.web.validators.UserValidator;

import com.buttoncode.mstest.core.services.EmailService;
import com.buttoncode.mstest.core.entities.Role;
import com.buttoncode.mstest.core.entities.User;
import com.buttoncode.mstest.core.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
@Secured(SecurityUtil.MANAGE_USERS)
public class UserController extends MStestAdminBaseController
{
	private static final String viewPrefix = "users/";
	@Autowired protected SecurityService securityService;

	@Autowired private UserValidator userValidator;
	@Autowired protected PasswordEncoder passwordEncoder;

	@Autowired protected TemplateEngine templateEngine;
	@Autowired protected EmailService emailService;

	@Override
	protected String getHeaderTitle()
	{
		return "Manage Users";
	}
	
	@ModelAttribute("rolesList")
	public List<Role> rolesList(){
		return securityService.getAllRoles();
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUsers(Model model) {
		List<User> userSearch = securityService.getAllUsers();

		List<User> list = securityService.getAllUsers();
		model.addAttribute("users",list);
		model.addAttribute("usersearch",userSearch);
		return viewPrefix+"users";
	}
	
	@RequestMapping(value="/users/new", method=RequestMethod.GET)
	public String createUserForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		//model.addAttribute("rolesList",securityService.getAllRoles());		
		
		return viewPrefix+"create_user";
	}

	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return viewPrefix+"create_user";
		}
		String password = user.getPassword();
		String encodedPwd = passwordEncoder.encode(password);
		user.setPassword(encodedPwd);
		User persistedUser = securityService.createUser(user);
		logger.debug("Created new User with id : {} and name : {}", persistedUser.getId(), persistedUser.getFirstname());
		redirectAttributes.addFlashAttribute("info", "User created successfully");
		return "redirect:/users";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String editUserForm(@PathVariable Integer id, Model model) {

		User user = securityService.getUserById(id);

		Map<Integer, Role> assignedRoleMap = new HashMap<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles)
		{
			assignedRoleMap.put(role.getId(), role);
		}
		List<Role> userRoles = new ArrayList<>();
		List<Role> allRoles = securityService.getAllRoles();
		for (Role role : allRoles)
		{
			if(assignedRoleMap.containsKey(role.getId())){
				userRoles.add(role);
			} else {
				userRoles.add(null);
			}
		}
		user.setRoles(userRoles);

		model.addAttribute("user",user);

		return viewPrefix+"edit_user";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, BindingResult result, 
			Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			return viewPrefix+"edit_user";
		}
		User persistedUser = securityService.updateUser(user);
		logger.debug("Updated user with id : {} and name : {}", persistedUser.getId(), persistedUser.getFirstname());
		redirectAttributes.addFlashAttribute("info", "User updates successfully");
		return "redirect:/users";
	}

	@RequestMapping(value="/users/forgotPwd/{id}", method=RequestMethod.GET)//wysylanie tokena na maila
	public String handleForgotPwd(@PathVariable("id") Integer id, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		User user = securityService.getUserById(id);
		String email = user.getEmail();

		try
		{
			String token = securityService.resetPassword(email);
			String resetPwdURL = WebUtils.getURLWithContextPath(request)+"/resetPwd?email="+email+"&token="+token;
			logger.debug(resetPwdURL);
			this.sendForgotPasswordEmail(email, resetPwdURL);
			redirectAttributes.addFlashAttribute("msg", getMessage(MessageCodes.INFO_PASSWORD_RESET_LINK_SENT));
		} catch (MStestException e)
		{
			logger.error(e);
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
		}
		return "redirect:/users";
	}

	protected void sendForgotPasswordEmail(String email, String resetPwdURL)
	{
		try {

			// Prepare the evaluation context
			final Context ctx = new Context();
			ctx.setVariable("resetPwdURL", resetPwdURL);

			// Create the HTML body using Thymeleaf
			final String htmlContent = this.templateEngine.process("forgot-password-email", ctx);

			emailService.sendEmail(email, getMessage(MessageCodes.LABEL_PASSWORD_RESET_EMAIL_SUBJECT), htmlContent);
		} catch (MStestException e) {
			logger.error(e);
		}
	}

}
