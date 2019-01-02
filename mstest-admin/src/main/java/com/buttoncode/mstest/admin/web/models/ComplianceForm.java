package com.buttoncode.mstest.admin.web.models;

import com.buttoncode.mstest.core.entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class ComplianceForm {

    private Integer id;
    private Date dateoftrainging;
    private Date expirydateoftraining;
    private String waitingtime;
    private String statuscompliance;
    private Date createdon;

    private Employee employee;
    private WaitingTime waitingTime;
    private StatusCompliance statusCompliance;

    private String filename;
    private MultipartFile image;

    private MultipartFile [] image2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateoftrainging() {
        return dateoftrainging;
    }

    public void setDateoftrainging(Date dateoftrainging) {
        this.dateoftrainging = dateoftrainging;
    }

    public Date getExpirydateoftraining() {
        return expirydateoftraining;
    }

    public void setExpirydateoftraining(Date expirydateoftraining) {
        this.expirydateoftraining = expirydateoftraining;
    }

    public String getWaitingtime() {
        return waitingtime;
    }

    public void setWaitingtime(String waitingtime) {
        this.waitingtime = waitingtime;
    }

    public String getStatuscompliance() {
        return statuscompliance;
    }

    public void setStatuscompliance(String statuscompliance) {
        this.statuscompliance = statuscompliance;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WaitingTime getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(WaitingTime waitingTime) {
        this.waitingTime = waitingTime;
    }

    public StatusCompliance getStatusCompliance() {
        return statusCompliance;
    }

    public void setStatusCompliance(StatusCompliance statusCompliance) {
        this.statusCompliance = statusCompliance;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile[] getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile[] image2) {
        this.image2 = image2;
    }

    public ComplianceCodeOfEthic toComplianceCodeOfEthic(){
        ComplianceCodeOfEthic c = new ComplianceCodeOfEthic();
        c.setId(id);
        c.setDateoftrainging(dateoftrainging);
        c.setExpirydateoftraining(expirydateoftraining);


        c.setFilename(filename);

        c.setEmployee(employee);
        c.setWaitingtime(waitingTime);
        c.setStatusCompliance(statusCompliance);

//        c.setCreatedon(createdon);
        return c;
    }

    public ComplianceCodeOfEthic toComplianceStatus(){
        ComplianceCodeOfEthic c = new ComplianceCodeOfEthic();
        c.setStatusCompliance(statusCompliance);
        return c;
    }

    public static ComplianceForm complianceForm1 (ComplianceCodeOfEthic complianceCodeOfEthic){
        ComplianceForm c = new ComplianceForm();
        c.setId(complianceCodeOfEthic.getId());
        c.setDateoftrainging(complianceCodeOfEthic.getDateoftrainging());
        c.setExpirydateoftraining(complianceCodeOfEthic.getExpirydateoftraining());
        c.setWaitingTime(complianceCodeOfEthic.getWaitingtime());
        c.setStatusCompliance(complianceCodeOfEthic.getStatusCompliance());
//        c.setCreatedon(complianceCodeOfEthic.getCreatedon());
        return c;
    }

    public static ComplianceForm complianceFormUpdate (ComplianceCodeOfEthic complianceCodeOfEthic){
        ComplianceForm c = new ComplianceForm();
        c.setId(complianceCodeOfEthic.getId());
        c.setDateoftrainging(complianceCodeOfEthic.getDateoftrainging());
        c.setExpirydateoftraining(complianceCodeOfEthic.getExpirydateoftraining());
        c.setWaitingTime(complianceCodeOfEthic.getWaitingtime());
        c.setStatusCompliance(complianceCodeOfEthic.getStatusCompliance());
//        c.setCreatedon(complianceCodeOfEthic.getCreatedon());
        return c;
    }

    public ComplianceAntiCorruptionPolicy toComplianceAntiCorruptionPolicy(){
        ComplianceAntiCorruptionPolicy c = new ComplianceAntiCorruptionPolicy();
        c.setId(id);
        c.setDateoftrainging(dateoftrainging);
        c.setExpirydateoftraining(expirydateoftraining);


        c.setFilename(filename);

        c.setEmployee(employee);
        c.setWaitingtime(waitingTime);
        c.setStatusCompliance(statusCompliance);

//        c.setCreatedon(createdon);
        return c;
    }
}
