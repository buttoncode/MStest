package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.admin.web.models.StocktakingSearchForm;
import com.buttoncode.mstest.admin.web.specifications.SearchStockstakingSpecification;
import com.buttoncode.mstest.core.MStestException;
import com.buttoncode.mstest.core.entities.Employee;
import com.buttoncode.mstest.core.entities.Stocktaking;
import com.buttoncode.mstest.core.entities.StocktakingHardware;
import com.buttoncode.mstest.core.entities.StocktakingSoftware;
import com.buttoncode.mstest.core.service.EmployeeService;
import com.buttoncode.mstest.core.service.PeriodicService;
import com.buttoncode.mstest.core.service.StocktakingHardwareService;
import com.buttoncode.mstest.core.service.StocktakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Controller
@Secured(SecurityUtil.MANAGE_STOCKTAKING)
public class StocktakingController extends MStestAdminBaseController {

    String colorFlashAttribute = "info";
    String informationFlashAttribute = null;

    @Autowired
    StocktakingHardwareService stocktakingHardwareService;
    @Autowired
    StocktakingService stocktakingService;
    @Autowired
    PeriodicService periodicService;
    @Autowired
    EmployeeService employeeService;

    private static final String viewPrefix = "stocktaking/";

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Stocktaking";
    }

    @RequestMapping(value = "/stocktakings", method = RequestMethod.GET)
    public String listStocktaking (Model model, AuthenticatedUser authenticatedUser){

        StocktakingSearchForm stocktakingSearchForm = new StocktakingSearchForm();
        model.addAttribute("stocktakingsearch", stocktakingSearchForm);

        List<StocktakingHardware> stocktakingHardwares = stocktakingService.getAllStocktakingHardware();
        List<StocktakingSoftware> stocktakingSoftwares = stocktakingService.getAllStocktakingSoftware();

        List<Stocktaking> stocktakings = new ArrayList<>();
        stocktakings.addAll(stocktakingHardwares);
        stocktakings.addAll(stocktakingSoftwares);

        model.addAttribute("stocktaking", stocktakings);

        return viewPrefix + "stocktaking";
    }

    @RequestMapping(value = "/stocktakings", method= RequestMethod.POST)
    public ModelAndView Search(
            @ModelAttribute ("stocktakingsearch") StocktakingSearchForm stocktakingSearchForm,
            Model model, AuthenticatedUser authenticatedUser){
        ModelAndView modelAndView = new ModelAndView(viewPrefix+"stocktaking");

        SearchStockstakingSpecification searchStockstakingSpecification = new SearchStockstakingSpecification(stocktakingSearchForm);
        model.addAttribute("stocktakingsearch", stocktakingSearchForm);

        return modelAndView;
    }

    @RequestMapping(value="/stocktakings/{id}", method= RequestMethod.GET)
    public String editStocktakingForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes, AuthenticatedUser authenticatedUser) {

        StocktakingHardware stocktakingHardwares = stocktakingService.getOneByIdStocktakingHardware(id);
        StocktakingSoftware stocktakingSoftwares = stocktakingService.getOneByIdStocktakingSoftware(id);

        Stocktaking stocktakings = null;

        if (stocktakingHardwares == null){
            model.addAttribute("stocktakingForm", stocktakingSoftwares);
            return viewPrefix + "stocktakingSoftware_edit";
        }
            model.addAttribute("stocktakingForm", stocktakingHardwares);
            return viewPrefix + "stocktakingHardware_edit";
    }

    @RequestMapping(value="/stocktakings/employees/{id}", method= RequestMethod.GET)
    public String editStocktakingsEmployee(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes, AuthenticatedUser authenticatedUser) {

        List<String> opisInfo = new ArrayList<String>();
        List<String> opisSuccess = new ArrayList<String>();
        List<String> opisWarning = new ArrayList<String>();
        List<String> opisError = new ArrayList<String>();

        Employee employeeById = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employeeById);

        return viewPrefix+"stocktaking_employees_edit";
    }

    protected void sendOrderStatusUpdateEmail1(Stocktaking stocktaking)
    {
        try {
            // Prepare the evaluation context
            final Context ctx = new Context();
            ctx.setVariable("stocktaking", stocktaking);

        } catch (MStestException e) {
            logger.error(e);
        }
    }
}
