package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.*;
import com.buttoncode.mstest.core.repository.*;
import com.buttoncode.mstest.core.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeedataRepository employeedataRepository;
    @Autowired
    TaskbookRepository taskbookRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StatusEmployeeRepository statusEmployeeRepository;
    @Autowired
    UserRepository userRepository;

    public User getUserByEmail (String email) {return userRepository.findByEmail(email);}

    public Employee getEmployeeById(Integer id) { return employeeRepository.findOne(id); }

    public List <Employee> getSearch (Specification<Employee> employeeSpecification){
        return employeeRepository.findAll(employeeSpecification);}

    public List<Employee> getAllWithoutEmployee(){
        return employeeRepository.findAllEmployeeWithoutTaskbook();
    }
    //## WERYFIKACJA ##//



    public List<Employee> getEmployeeListByNumbersap (Integer numbersap) {return employeeRepository.findListByNumbersap(numbersap);}

    public Employee getEmployeeByNumbersap(Integer numbersap) { return employeeRepository.findByNumbersap(numbersap); }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> getAllEmployeeSort()
    {
        Sort sort = new Sort (Sort.Direction.DESC, "employeedata.id");
        return employeeRepository.findAll(sort);
    }

    public List<Employee> getAllEmployeeByDepartament()
    {
        Sort sort = new Sort (Sort.Direction.DESC, "employeedata.id");
        return employeeRepository.findAll(sort);
    }

    public Employee updateFileEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee updateEmployeeStatus(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee (Integer id){
        employeeRepository.delete(id);
    }

    public Employee EmployeeCheckNumberSAP (Integer numbersap) {
        List <Employee> getEmployeeNumberSAP = employeeRepository.findEmployeesByNumbersap(numbersap);
        int i = 0;
        Employee employeeGoog = null;
        for(Employee employee : getEmployeeNumberSAP) {
            if (employee.getStatusEmployee().getName().equals("Zwolniony")) {
                System.out.println("petla: [" + employee.getNumbersap() + "] " + employee.getFirstname() + " // Status " + employee.getStatusEmployee().getName() + " jest NIEAKTYWNY");
                i++;
            } else {
                System.out.println("petla: [" + employee.getNumbersap() + "] " + employee.getFirstname() + " // Status " + employee.getStatusEmployee().getName() + " jest AKTYWNY albo inny status");

            }
            if(i>0){
                employeeGoog = null;
            }else{
                employeeGoog = employee;
            }
        }
        return employeeGoog;}

    public List <Employee> getEmployeeRoleDepartament (String departament) {

        List<Employee> getEmployeeNumberSAP = employeeRepository.findAll();
        return getEmployeeNumberSAP;
    }
    public List <Employee> getEmployeeByNumberSAP (Integer numbersap) {
        List<Employee> getEmployeeNumberSAP = employeeRepository.findEmployeesByNumbersap(numbersap);
        return getEmployeeNumberSAP;
    }
    public List <Employee> getEmployeeBySearch (Integer numbersap, String firstname, String lastname, String depShortname, String depName, String status) {

        List<Employee> getEmployeeNumberSAP = employeeRepository.findAll();
        return getEmployeeNumberSAP;
    }

    public List<Employee> getAllEmployeeWithStatusEmployee(String status) {
        return employeeRepository.findAllEmployeeWithStatus(status);
    }

    /**EmployeedataService**/
    public List <Employeedata> getSearch2 (Specification<Employeedata> employeedataSpecification){
        return employeedataRepository.findAll(employeedataSpecification);
    }

    public Employeedata updateEmployeedata(Employeedata employeedata){
        return employeedataRepository.save(employeedata);
    }
    public Employeedata getEmployeedataById(Integer id) {return employeedataRepository.findOne(id);}
    public List<Employeedata> getAllEmployeesdata() { return employeedataRepository.findAll(); }
    public List<Employeedata> getAllEmployeesdataByDepartament(String departament) {

        return employeedataRepository.findAll(); }
    public List<Employeedata> getAllEmployeesdataByIdEmployee(Integer employeeId) {

        return employeedataRepository.findAll(); }
    public List<Employeedata> getAllEmployeesdataByIdEmployeeAnddepartmentshortname(Integer employeeId, String shortname) {

        return employeedataRepository.findAll(); }
    public List<Employeedata> getEmployeedataRoleDepartament (String departament) {

        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.findAll();
        return getEmployeedataNumberSAP;
    }
    public List<Employeedata> getEmployeedataByNumbersap (Integer numbersap) {

        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.findAll();
        return getEmployeedataNumberSAP;
    }

    /**departmentservice**/
    public Department getByShortname(String shortname) {return departmentRepository.findOneByShortname(shortname);}
    public Department getByName(String name) {return departmentRepository.findOneByName(name);}
    public List<Department> getAllByShortname(String shortname) {return departmentRepository.findAllByShortname(shortname);}

    /**TaskBookService**/
    public Taskbook uploadTaskBook(Taskbook taskbook){
        return taskbookRepository.save(taskbook);
    }
    public Taskbook getTaskbookById(Integer id) {return taskbookRepository.findOne(id);}


    public List<Taskbook> getTaskbookRoleDepartament (String departament) {
//        List<Taskbook> getTaskbookNumberSAP = taskbookRepository.findTaskbookeBydepartments(departament);
        List<Taskbook> getTaskbookNumberSAP = taskbookRepository.findAll();
        return getTaskbookNumberSAP;
    }
}
