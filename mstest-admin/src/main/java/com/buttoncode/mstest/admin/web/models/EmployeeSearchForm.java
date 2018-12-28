package com.buttoncode.mstest.admin.web.models;


public class EmployeeSearchForm {

    //Employee
    private String firstname;
    private String lastname;
    private String numbersap;
    //StatusEmployee
    private String statusemployee;
    //Departament
    private String departmentname;
    private String departmentshortname;


    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumbersap() {
        return numbersap;
    }
    public void setNumbersap(String numbersap) {
        this.numbersap = numbersap;
    }

    public String getStatusemployee() {
        return statusemployee;
    }
    public void setStatusemployee(String statusemployee) {
        this.statusemployee = statusemployee;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDepartmentshortname() {
        return departmentshortname;
    }

    public void setDepartmentshortname(String departmentshortname) {
        this.departmentshortname = departmentshortname;
    }
}
