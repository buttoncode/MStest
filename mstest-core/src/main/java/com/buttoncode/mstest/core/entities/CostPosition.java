package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cost_position")
public class CostPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_mpk")
    private String namempk;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="cost_position_department",
            joinColumns={@JoinColumn(name="COST_POSITION_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")})
    private List<Department> departments;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamempk() {
        return namempk;
    }
    public void setNamempk(String namempk) {
        this.namempk = namempk;
    }

    public List<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
