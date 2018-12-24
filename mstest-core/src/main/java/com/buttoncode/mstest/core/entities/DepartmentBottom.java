package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments")
@DiscriminatorValue("bottom")
public class DepartmentBottom extends Department implements Serializable{

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentMiddle departmentMiddle;

    public DepartmentMiddle getDepartmentMiddle() {
        return departmentMiddle;
    }
    public void setDepartmentMiddle(DepartmentMiddle departmentMiddle) {
        this.departmentMiddle = departmentMiddle;
    }
}
