package com.buttoncode.mstest.admin.web.validators;

import org.springframework.stereotype.Component;

@Component
public class EmployeeTaskBookFileFormValidator{

//    @Autowired protected EmployeeRepository employeeRepository;

    public String FirstPartFileName (String fileName){
        int endIndex = fileName.indexOf("_");
        String name = fileName.substring(0,endIndex);
        return name;
    }

    public String DatePartFileName (String fileName){
        int startIndex = fileName.indexOf("_");
        int endIndex = fileName.indexOf(".");
        String name = fileName.substring(startIndex+1,endIndex);
        return name;
    }

    public String LastPartFileName (String fileName){
        int endIndex = fileName.lastIndexOf(".");
        String name = fileName.substring(endIndex);
        return name;
    }

}
