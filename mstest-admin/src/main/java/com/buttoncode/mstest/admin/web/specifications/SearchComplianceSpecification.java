package com.buttoncode.mstest.admin.web.specifications;

import com.buttoncode.mstest.admin.web.models.ComplianceSearchForm;
import com.buttoncode.mstest.core.entities.ComplianceCodeOfEthic;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchComplianceSpecification implements Specification<ComplianceCodeOfEthic> {

    private ComplianceSearchForm complianceSearchForm;
    private List<Predicate> predicates;

    public SearchComplianceSpecification(ComplianceSearchForm complianceSearchForm) {
        this.complianceSearchForm = complianceSearchForm;
        predicates = new ArrayList<Predicate>();
    }

    @Override
    public Predicate toPredicate(Root<ComplianceCodeOfEthic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

//        Join<Employee, ?> partManufacturer = root.join("Numbersap"); /* ? - uzupelnic*/

        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getNumbersap()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("numbersap"), complianceSearchForm.getNumbersap()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getFirstname()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("firstname"), complianceSearchForm.getFirstname()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getLastname()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("lastname"), complianceSearchForm.getLastname()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getDateoftrainging()))) {
            predicates.add(criteriaBuilder.equal(root.get("dateoftraining"), complianceSearchForm.getDateoftrainging()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getExpirydateoftraining()))) {
            predicates.add(criteriaBuilder.equal(root.get("expirydateoftraining"), complianceSearchForm.getExpirydateoftraining()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getStatusemployee()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("statusEmployee").get("name"), complianceSearchForm.getStatusemployee()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(complianceSearchForm.getStatusemployee()))) {
            predicates.add(criteriaBuilder.equal(root.get("statusCompliance").get("name"), complianceSearchForm.getStatuscompliance()));
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public Date checkDate (Integer mounth) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MONTH, mounth);
        return c.getTime();
    }

}
