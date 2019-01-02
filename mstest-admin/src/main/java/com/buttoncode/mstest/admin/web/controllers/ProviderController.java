package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.core.MStestException;
import com.buttoncode.mstest.core.entities.Provider;
import com.buttoncode.mstest.core.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import java.util.List;

@Controller
@Secured(SecurityUtil.MANAGE_PROVIDERS)
public class ProviderController extends MStestAdminBaseController {

    @Autowired
    ProviderService providerService;

    private static final String viewPrefix = "providers/";

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Providers";
    }


    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public String listProvider (Model model, AuthenticatedUser authenticatedUser){
        List<Provider> providers = providerService.getAllProviders();

        model.addAttribute("providers", providers);

        return viewPrefix + "provider";
    }

    @RequestMapping(value="/providers/{id}", method= RequestMethod.GET)
    public String editProviderForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes, AuthenticatedUser authenticatedUser) {

        Provider provider = providerService.getProviderById(id);

            model.addAttribute("providerForm", provider);
            return viewPrefix + "provider_edit";
    }

    protected void sendOrderStatusUpdateEmail1(Provider provider)
    {
        try {
            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("provider", provider);

        } catch (MStestException e) {
            logger.error(e);
        }
    }
}
