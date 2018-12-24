package com.buttoncode.mstest.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sections")
public class SectionTop implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "short_name")
    private String shortname;

    @Column(name = "tree", insertable = false, updatable = false)
    private String tree;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sectionTop")
    public List<DepartmentMiddle> departmentMiddles;

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

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public List<DepartmentMiddle> getDepartmentMiddles() {
        return departmentMiddles;
    }
    public void setDepartmentMiddles(List<DepartmentMiddle> departmentMiddles) {
        this.departmentMiddles = departmentMiddles;
    }

    @Override
    public String toString() {
        return "SectionTop{" +
                "id=" + id +
                '}';
    }
}
