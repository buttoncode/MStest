package com.buttoncode.mstest.admin.web.validators;

import org.springframework.stereotype.Component;

@Component
public class EmployeeFileFormValidator  {


    public String FirstPartFileName (String fileName){
        int endIndex = fileName.indexOf(".");
        String name = fileName.substring(0,endIndex);
        return name;
    }

    public String CenterPartFileName (String fileName){
        int startIndex = fileName.indexOf(".");
        int endIndex = fileName.indexOf(".");
        String name = fileName.substring(startIndex+1,endIndex);
        return name;
    }

    public String LastPartFileName (String fileName){
        int endIndex = fileName.lastIndexOf(".");
        String name = fileName.substring(endIndex+1);
        return name;
    }
}
