package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.web.models.MyAccountForm;
import com.buttoncode.mstest.core.entities.User;
import com.buttoncode.mstest.core.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyAccountController extends MStestAdminBaseController
{
    private static final String viewPrefix = "profile/";

    @Autowired protected SecurityService securityService;
    @Autowired protected PasswordEncoder passwordEncoder;

    @Override
    protected String getHeaderTitle() {
        return "Manage Profile";
    }

    @RequestMapping(value="myAccount", method= RequestMethod.GET)
    public String editProfileForm(Model model, AuthenticatedUser authenticatedUser) {

        User user = securityService.findUserByEmail(authenticatedUser.getUsername());

        MyAccountForm myAccountForm = new MyAccountForm();
        model.addAttribute("passwordchange", myAccountForm);

        model.addAttribute("profile",user);

        return viewPrefix+"myAccount";
    }

    @RequestMapping(value="/myAccount", method=RequestMethod.POST)
    public String updateMyAccount(@ModelAttribute("passwordchange") MyAccountForm myAccountForm,
                                  BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        User user = securityService.findUserByEmail(myAccountForm.getEmail());

        if (passwordEncoder.matches(myAccountForm.getOldPassword(), user.getPassword())){

            if (myAccountForm.getNew1Password().equals(myAccountForm.getNew2Password())){

                myAccountForm.setPassword(passwordEncoder.encode(myAccountForm.getNew1Password()));
                myAccountForm.setId(user.getId());

                User userUpdate = myAccountForm.toUser();
                securityService.updateUserPwd(userUpdate);
                opisSuccess.add("Sukces. Aktualizacja hasla powiodla sie.");
            }else{
                opisError.add("Podane haslo jest bledne.");
            }

        }else{
            opisError.add("Podane haslo jest bledne.");
        }

        if(opisInfo.size()>0){
            redirectAttributes.addFlashAttribute("info", String.join("\n", opisInfo));
        }if(opisSuccess.size()>0) {
            redirectAttributes.addFlashAttribute("success", String.join("\n", opisSuccess));
        }if(opisError.size()>0) {
            redirectAttributes.addFlashAttribute("error", String.join("\n", opisError));
        }if(opisWarning.size()>0){
            redirectAttributes.addFlashAttribute("warning", String.join("\n", opisWarning));
        }
        return "redirect:/myAccount";
    }
}
