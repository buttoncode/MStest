package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "number_sap")
    private Integer numbersap;

    @Column(name = "created_on")
    private Date createdon = new Date();

    @ManyToOne
    @JoinColumn(name = "status_employee_id")
    private StatusEmployee statusEmployee;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "employee")
    private List<Employeedata> employeedata;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "employees")
    private List<StocktakingHardware> stocktakingHardwares;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getNumbersap() {
        return numbersap;
    }
    public void setNumbersap(Integer numbersap) {
        this.numbersap = numbersap;
    }
    public Date getCreatedon() {
        return createdon;
    }
    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public StatusEmployee getStatusEmployee() {
        return statusEmployee;
    }
    public void setStatusEmployee(StatusEmployee statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    public List<Employeedata> getEmployeedata() {
        return employeedata;
    }
    public void setEmployeedata(List<Employeedata> employeedata) {
        this.employeedata = employeedata;
    }

    public List<StocktakingHardware> getStocktakingHardwares() {
        return stocktakingHardwares;
    }
    public void setStocktakingHardwares(List<StocktakingHardware> stocktakingHardwares) {
        this.stocktakingHardwares = stocktakingHardwares;
    }
}