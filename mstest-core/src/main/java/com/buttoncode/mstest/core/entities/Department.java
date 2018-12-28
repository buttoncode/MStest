package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tree")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "short_name")
    private String shortname;

    @Column(name = "tree", insertable = false, updatable = false)
    private String tree;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "department")
    private List<Employeedata> employeedata;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="cost_position_department",
            joinColumns={@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="COST_POSITION_ID", referencedColumnName="ID")})
    private List<CostPosition> costPositions;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_department_permission",
            joinColumns={@JoinColumn(name="DEPARTMENT_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")})
    private List<User> users;

    @ManyToOne
    private SectionTop sectionTop;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getTree() {
        return tree;
    }
    public void setTree(String tree) {
        this.tree = tree;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public SectionTop getSectionTop() {
        return sectionTop;
    }
    public void setSectionTop(SectionTop sectionTop) {
        this.sectionTop = sectionTop;
    }

    public List<CostPosition> getCostPositions() {
        return costPositions;
    }
    public void setCostPositions(List<CostPosition> costPositions) {
        this.costPositions = costPositions;
    }

    public List<Employeedata> getEmployeedata() {
        return employeedata;
    }
    public void setEmployeedata(List<Employeedata> employeedata) {
        this.employeedata = employeedata;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                '}';
    }
}
