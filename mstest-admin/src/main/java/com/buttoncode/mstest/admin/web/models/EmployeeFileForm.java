package com.buttoncode.mstest.admin.web.models;

import com.buttoncode.mstest.core.entities.*;
import com.buttoncode.mstest.core.service.EmployeedataServis;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EmployeeFileForm {

    @Autowired
    EmployeedataServis employeedataServis;

    //Employee
    private Employee employee;
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer numersap;
    private Date createdon = new Date();

    //Status
    private StatusEmployee statusEmployee;

    //Employeedata
    private Employeedata employeedata;
    private Integer employeeid;
    private String workposition;
    private Date beginningofvalidity;
    private String groupdesignation;

    //TaskBook
    private Taskbook taskbook;
    private String namefile;
    private Date taksbookdate;

    //Departament
    private Department departament;

    //Addition
    @NotNull
    @NotEmpty
    private MultipartFile image;

    @NotNull
    @NotEmpty
    private MultipartFile [] image2;

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

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
    public Integer getNumersap() {
        return numersap;
    }
    public void setNumersap(Integer numersap) {
        this.numersap = numersap;
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

    public Employeedata getEmployeedata() {
        return employeedata;
    }
    public void setEmployeedata(Employeedata employeedata) {
        this.employeedata = employeedata;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }
    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
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

    public Taskbook getTaskbook() {
        return taskbook;
    }
    public void setTaskbook(Taskbook taskbook) {
        this.taskbook = taskbook;
    }

    public String getNamefile() {
        return namefile;
    }
    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }
    public Date getTaksbookdate() {
        return taksbookdate;
    }
    public void setTaksbookdate(Date taksbookdate) {
        this.taksbookdate = taksbookdate;
    }

    public Department getDepartament() {
        return departament;
    }
    public void setDepartament(Department departament) {
        this.departament = departament;
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

    public Employee toEmployee(){
        Employee e = new Employee();
        e.setId(id);
        e.setFirstname(firstname);
        e.setLastname(lastname);
        e.setNumbersap(numersap);
        e.setCreatedon(createdon);
        e.setStatusEmployee(statusEmployee);

/*        List<Employeedata> lista1 = new ArrayList<>();
        lista1.add(employeedata);
        e.setEmployeedata(lista1);*/

        return e;
    }

    public Employee toEmployeeStatus(){
        Employee e = new Employee();
        e.setStatusEmployee(statusEmployee);
        return e;
    }

    public Employeedata toEmployeedata(){
        Employeedata ed = new Employeedata();
        ed.setWorkposition(workposition);
        ed.setBeginningofvalidity(beginningofvalidity);
        ed.setGroupdesignation(groupdesignation);
        ed.setEmployee(employee);
        ed.setDepartment(departament);
        return ed;
    }

    public Taskbook toTaskbook(){
        Taskbook tb = new Taskbook();
        tb.setNamefile(namefile);
        tb.setTaskbookdate(taksbookdate);
        tb.setEmployee(employee);
        return tb;
    }

    public static EmployeeFileForm employeeFileForm(Employee employee){
        EmployeeFileForm e = new EmployeeFileForm();
        e.setId(employee.getId());
        e.setFirstname(employee.getFirstname());
        e.setLastname(employee.getLastname());
        e.setStatusEmployee(employee.getStatusEmployee());
        e.setCreatedon(employee.getCreatedon());
        return e;
    }

}
