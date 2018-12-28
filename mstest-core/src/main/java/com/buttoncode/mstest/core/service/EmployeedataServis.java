package com.buttoncode.mstest.core.service;

import com.buttoncode.mstest.core.entities.Department;
import com.buttoncode.mstest.core.entities.Employeedata;
import com.buttoncode.mstest.core.entities.User;
import com.buttoncode.mstest.core.repository.DepartmentRepository;
import com.buttoncode.mstest.core.repository.EmployeedataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeedataServis {

    @Autowired
    EmployeedataRepository employeedataRepository;
    @Autowired
    DepartmentRepository departamentRepository;

    public Employeedata getEmployeedataById(Integer id) {return employeedataRepository.findOne(id);}

    public List <Employeedata> getSearch (Specification<Employeedata> employeedataSpecification){
        return employeedataRepository.findAll(employeedataSpecification);
    }

    public List<Employeedata> showToEmployeedataAuthentication (User user) {
        List<Employeedata> employeedatasList = new ArrayList(); //pusta EmployeeData lista
        List<Department> departments = user.getDepartments(); //pobieranie ROLI - Departamenty
        for (int i=0; i<departments.size(); i++){
            employeedatasList.addAll(departments.get(i).getEmployeedata());
        }
        List<Employeedata> employeedatasListAfterSorting = sortedByBeginningofvalidity(employeedatasList);

        return employeedatasListAfterSorting;
    }

    public List<Employeedata> sortedByBeginningofvalidity (List<Employeedata> employeedatasList){
        Collections.sort(employeedatasList, new Comparator<Employeedata>() {
            @Override
            public int compare(Employeedata o1, Employeedata o2) {
                return o1.getBeginningofvalidity().compareTo(o2.getBeginningofvalidity());
            }
        });
        return employeedatasList;
    }

    public List<Employeedata> removeDuplicateListEmployeedata (List<Employeedata> employeedatasList){

        for (int i=0; i<employeedatasList.size() ;i++){
            for (int j=1; j<employeedatasList.size(); j++){
                if(i!=j &&
                   employeedatasList.get(i).getEmployee().getId() == employeedatasList.get(j).getEmployee().getId() &&
                   employeedatasList.get(i).getDepartment().getId() == employeedatasList.get(j).getDepartment().getId()){
                    if(employeedatasList.size() == i+1){
                        i = i-1;
                    }
                    employeedatasList.remove(j);
                    employeedatasList.size();
                }
            }
        }
        return employeedatasList;
    }

    public List<Employeedata> getEmployeedataAuthenticationWithSearch (List<Employeedata> employeedatasList, List<Employeedata> searchEmployeedataList){
        List<Employeedata> employeedatasListAfterSearch = new ArrayList(); //pusta EmployeeData lista
        for(int i=0; i<searchEmployeedataList.size(); i++){
            for(int y=0; y<employeedatasList.size(); y++){

                if(employeedatasList.get(y).getId().equals(searchEmployeedataList.get(i).getId())){
                    employeedatasListAfterSearch.add(searchEmployeedataList.get(i));
                }
            }
        }

        return employeedatasListAfterSearch;
    }

    public List<Employeedata> checkToEmployeedataAuthentication (List<Employeedata> employeedatasList, Employeedata employeedataById){
        List<Employeedata> employeedataListByEmployeeAndDepartament = new ArrayList<>();
        for (int x = 0; x < employeedatasList.size(); x++) {
                for (int y = 0; y < employeedatasList.get(x).getDepartment().getCostPositions().size(); y++) {
                    if (employeedataById.getDepartment().getCostPositions().get(0).getNamempk().equals(employeedatasList.get(x).getDepartment().getCostPositions().get(y).getNamempk()) && employeedataById.getEmployee().getNumbersap().equals(employeedatasList.get(x).getEmployee().getNumbersap())) {
                        employeedataListByEmployeeAndDepartament.add(employeedatasList.get(x));
                    }
                }
        }
        return employeedataListByEmployeeAndDepartament;
    }

    public List<Employeedata> getAllEmployeesdataByIdEmployee(Integer employeeId) {
        return employeedataRepository.findAllByIdEmployee(employeeId); }

    //## WERYFIKACJA ##//


    public Employeedata updateEmployeedata(Employeedata employeedata){
        return employeedataRepository.save(employeedata);
    }

    public List<Employeedata> getAllEmployeesdata() { return employeedataRepository.findAll(); }
    public List<Employeedata> getAllEmployeesdataByDepartament(String departament) {
//        return employeedataRepository.findAllByDepartament(departament); }
        return employeedataRepository.findAll(); }
    public List<Employeedata> getAllEmployeesdataByIdEmployeeAnddepartmentshortname(Integer employeeId, String shortname) {
//        return employeedataRepository.findAllByIdEmployeeAnddepartmentshortname(employeeId, shortname); }
        return employeedataRepository.findAll(); }
    public List<Employeedata> getEmployeedataRoleDepartament (String departament) {
//        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.searchEmployeedataBydepartments(departament);
        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.findAll();
        return getEmployeedataNumberSAP;
    }
    public List<Employeedata> getEmployeedataByNumbersap (Integer numbersap) {
//        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.findAllByIdEmployee(numbersap);
        List<Employeedata> getEmployeedataNumberSAP = employeedataRepository.findAll();
        return getEmployeedataNumberSAP;
    }
}
