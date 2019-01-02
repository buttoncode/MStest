package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "compliance_code_of_ethic")
public class ComplianceCodeOfEthic implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date_of_training")
    private Date dateoftrainging;

    @ManyToOne
    @JoinColumn(name = "waiting_time_id")
    private WaitingTime waitingtime;

    @Column(name = "expiry_date_of_training")
    private Date expirydateoftraining;

    @ManyToOne
    @JoinColumn(name = "status_compliance_id")
    private StatusCompliance statusCompliance;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "created_on")
    private Date createdon = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateoftrainging() {
        return dateoftrainging;
    }

    public void setDateoftrainging(Date dateoftrainging) {
        this.dateoftrainging = dateoftrainging;
    }

    public WaitingTime getWaitingtime() {
        return waitingtime;
    }

    public void setWaitingtime(WaitingTime waitingtime) {
        this.waitingtime = waitingtime;
    }

    public Date getExpirydateoftraining() {
        return expirydateoftraining;
    }

    public void setExpirydateoftraining(Date expirydateoftraining) {
        this.expirydateoftraining = expirydateoftraining;
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

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }
}
