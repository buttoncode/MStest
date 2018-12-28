package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees_data")
public class Employeedata implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "work_position")
    private String workposition;

    @Column(name = "beginning_of_validity")
    private Date beginningofvalidity;

    @Column(name = "group_designation")
    private String groupdesignation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "created_on")
    private Date createdOn = new Date();

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

    public String getWorkposition() {
        return workposition;
    }
    public void setWorkposition(String workposition) {
        this.workposition = workposition;
    }

    public Date getBeginningofvalidity() {
        return beginningofvalidity;
    }
    public void setBeginningofvalidity(Date beginningofvalidity) {
        this.beginningofvalidity = beginningofvalidity;
    }

    public String getGroupdesignation() {
        return groupdesignation;
    }
    public void setGroupdesignation(String groupdesignation) {
        this.groupdesignation = groupdesignation;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employeedata{" +
                "id=" + id +
                '}';
    }
}
