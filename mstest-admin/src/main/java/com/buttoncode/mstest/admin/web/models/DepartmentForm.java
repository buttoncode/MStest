package com.buttoncode.mstest.admin.web.models;

import com.buttoncode.mstest.core.entities.CostPosition;
import com.buttoncode.mstest.core.entities.Department;
import com.buttoncode.mstest.core.entities.DepartmentMiddle;
import com.buttoncode.mstest.core.entities.SectionTop;

public class DepartmentForm {

    private Integer idd;
    private String name;
    private String shortname;

    private DepartmentMiddle departmentMiddle;
    private SectionTop sectionTop;

    private Integer idcp;
    private String namempk;

    public Integer getIdd() {
        return idd;
    }
    public void setIdd(Integer idd) {
        this.idd = idd;
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

    public DepartmentMiddle getDepartmentMiddle() {
        return departmentMiddle;
    }
    public void setDepartmentMiddle(DepartmentMiddle departmentMiddle) {
        this.departmentMiddle = departmentMiddle;
    }

    public SectionTop getSectionTop() {
        return sectionTop;
    }
    public void setSectionTop(SectionTop sectionTop) {
        this.sectionTop = sectionTop;
    }

    public Integer getIdcp() {
        return idcp;
    }
    public void setIdcp(Integer idcp) {
        this.idcp = idcp;
    }

    public String getNamempk() {
        return namempk;
    }
    public void setNamempk(String namempk) {
        this.namempk = namempk;
    }

    public static DepartmentForm formDepartment(Department department){
        DepartmentForm d = new DepartmentForm();
        d.setIdd(department.getId());
        d.setName(department.getName());
        d.setShortname(department.getShortname());
        return d;
    }

    public Department toDepartment(){
        Department d = new Department() {
        };
/*        Department d = new Department();
        d.setId(idd);
        d.setName(name);
        d.setShortname(shortname);
        d.setSectionTop(sectionTop);
        d.setDepartmentMiddle(departmentMiddle);*/
        return d;
    }

    public CostPosition toCostPosition(){
        CostPosition cp = new CostPosition();
        cp.setId(idcp);
        cp.setNamempk(namempk);
        return cp;
    }
}
