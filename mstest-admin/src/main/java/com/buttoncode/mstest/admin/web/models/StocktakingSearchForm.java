package com.buttoncode.mstest.admin.web.models;

public class StocktakingSearchForm {

    private Integer id;
    private String stocktakingnumbersap;
    private String stocktakingnumberms;

    private String devicetypename;
    private String employeenumbersap;
    private String employeefirstname;
    private String employeelastname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStocktakingnumbersap() {
        return stocktakingnumbersap;
    }

    public void setStocktakingnumbersap(String stocktakingnumbersap) {
        this.stocktakingnumbersap = stocktakingnumbersap;
    }

    public String getStocktakingnumberms() {
        return stocktakingnumberms;
    }

    public void setStocktakingnumberms(String stocktakingnumberms) {
        this.stocktakingnumberms = stocktakingnumberms;
    }

    public String getDevicetypename() {
        return devicetypename;
    }

    public void setDevicetypename(String devicetypename) {
        this.devicetypename = devicetypename;
    }

    public String getEmployeenumbersap() {
        return employeenumbersap;
    }

    public void setEmployeenumbersap(String employeenumbersap) {
        this.employeenumbersap = employeenumbersap;
    }

    public String getEmployeefirstname() {
        return employeefirstname;
    }

    public void setEmployeefirstname(String employeefirstname) {
        this.employeefirstname = employeefirstname;
    }

    public String getEmployeelastname() {
        return employeelastname;
    }

    public void setEmployeelastname(String employeelastname) {
        this.employeelastname = employeelastname;
    }
}
