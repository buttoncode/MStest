package com.buttoncode.mstest.admin.web.specifications;

import com.buttoncode.mstest.admin.web.models.EmployeeSearchForm;
import com.buttoncode.mstest.core.entities.Employee;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeSpecification implements Specification<Employee> {

    private EmployeeSearchForm employeeSearchForm;
    private List<Predicate> predicates;

    public SearchEmployeeSpecification(EmployeeSearchForm employeeSearchForm) {
        this.employeeSearchForm = employeeSearchForm;
        predicates = new ArrayList<Predicate>();
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getNumbersap()))) {
            predicates.add(criteriaBuilder.equal(root.get("numbersap"), employeeSearchForm.getNumbersap()));
        }
        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getFirstname()))) {
            predicates.add(criteriaBuilder.equal(root.get("firstname"), employeeSearchForm.getFirstname()));
        }
        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getLastname()))) {
            predicates.add(criteriaBuilder.equal(root.get("lastname"), employeeSearchForm.getLastname()));
        }
        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getDepartmentshortname()))) {
            predicates.add(criteriaBuilder.equal(root.get("department").get("shortname"), employeeSearchForm.getDepartmentshortname()));
        }
        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getDepartmentname()))) {
            predicates.add(criteriaBuilder.equal(root.get("department").get("name"), employeeSearchForm.getDepartmentname()));
        }
        if (! Strings.isNullOrEmpty(String.valueOf(employeeSearchForm.getStatusemployee()))) {
            predicates.add(criteriaBuilder.equal(root.get("statusEmployee").get("name"), employeeSearchForm.getStatusemployee()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
