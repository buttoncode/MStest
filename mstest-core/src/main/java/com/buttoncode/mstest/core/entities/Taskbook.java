package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_books")
public class Taskbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "name_file")
    private String namefile;

    @Column(name = "task_book_date")
    private Date taskbookdate;

    @JoinColumn(name = "created_on")
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

    public String getNamefile() {
        return namefile;
    }
    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public Date getTaskbookdate() {
        return taskbookdate;
    }
    public void setTaskbookdate(Date taskbookdate) {
        this.taskbookdate = taskbookdate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
