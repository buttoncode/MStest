package com.buttoncode.mstest.admin.web.validators;

import com.buttoncode.mstest.admin.web.models.EmployeeSearchForm;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSearchFormValidator {

    public EmployeeSearchForm searchCopyToDepartament2 (EmployeeSearchForm employeeSearchForm){

        return employeeSearchForm;
    }
}