package com.buttoncode.mstest.admin.web.models;

public class ComplianceSearchForm {

    //ComplianceCodeOfEthic
    private Integer id;
    private String dateoftrainging;
    private String expirydateoftraining;
    //StatusCompliance
    private String statuscompliance;
    //WaitingTime
    private String waitingtime;
    //Employee
    private String numbersap;
    private String firstname;
    private String lastname;
    //StatusEmployee
    private String statusemployee;

    //GETTERY and SETTERY
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateoftrainging() {
        return dateoftrainging;
    }
    public void setDateoftrainging(String dateoftrainging) {
        this.dateoftrainging = dateoftrainging;
    }

    public String getExpirydateoftraining() {
        return expirydateoftraining;
    }
    public void setExpirydateoftraining(String expirydateoftraining) {
        this.expirydateoftraining = expirydateoftraining;
    }

    public String getStatuscompliance() {
        return statuscompliance;
    }
    public void setStatuscompliance(String statuscompliance) {
        this.statuscompliance = statuscompliance;
    }

    public String getWaitingtime() {
        return waitingtime;
    }
    public void setWaitingtime(String waitingtime) {
        this.waitingtime = waitingtime;
    }

    public String getNumbersap() {
        return numbersap;
    }
    public void setNumbersap(String numbersap) {
        this.numbersap = numbersap;
    }

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

    public String getStatusemployee() {
        return statusemployee;
    }
    public void setStatusemployee(String statusemployee) {
        this.statusemployee = statusemployee;
    }
}
