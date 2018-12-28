package com.buttoncode.mstest.admin.web.controllers;

import com.buttoncode.mstest.admin.security.AuthenticatedUser;
import com.buttoncode.mstest.admin.security.SecurityUtil;
import com.buttoncode.mstest.core.entities.CostPosition;
import com.buttoncode.mstest.core.entities.Department;
import com.buttoncode.mstest.core.service.CostPositionService;
import com.buttoncode.mstest.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Secured(SecurityUtil.MANAGE_DEPARTMENT)
public class DepartmentController extends MStestAdminBaseController {

    private static final String viewPrefix = "departments/";

    @Autowired private DepartmentService departmentService;
    @Autowired private CostPositionService costPositionService;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage departments";
    }

    @ModelAttribute("costPositionList")
    public List<CostPosition> costPositionList(){
        return costPositionService.getAllCostPosition();
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String listEmployee (Model model, AuthenticatedUser authenticatedUser){

        List<Department> departmentList = departmentService.getAllDepartments();

        model.addAttribute("departamentList", departmentList);

        return viewPrefix + "departments";
    }

    @RequestMapping(value = "/departments/{idd}", method = RequestMethod.GET)
    public String editDepartmentForm (@PathVariable Integer idd, Model model){
        Department onedepartment = departmentService.getDepartmentById(idd);

        model.addAttribute("departments", onedepartment);
        return viewPrefix + "departments_edit";
    }

}
