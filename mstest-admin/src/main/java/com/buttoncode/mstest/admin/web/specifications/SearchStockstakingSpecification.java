package com.buttoncode.mstest.admin.web.specifications;

import com.buttoncode.mstest.admin.web.models.StocktakingSearchForm;
import com.buttoncode.mstest.core.entities.Stocktaking;
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

public class SearchStockstakingSpecification implements Specification<Stocktaking> {

    private StocktakingSearchForm stocktakingSearchForm;
    private List<Predicate> predicates;

    public SearchStockstakingSpecification(StocktakingSearchForm stocktakingSearchForm) {
        this.stocktakingSearchForm = stocktakingSearchForm;
        predicates = new ArrayList<Predicate>();
    }

    @Override
    public Predicate toPredicate(Root<Stocktaking> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getStocktakingnumbersap()))) {
            predicates.add(criteriaBuilder.equal(root.get("stocktakingnumbersap"), stocktakingSearchForm.getStocktakingnumbersap()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getStocktakingnumberms()))) {
            predicates.add(criteriaBuilder.equal(root.get("stocktakingnumberms"), stocktakingSearchForm.getStocktakingnumberms()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getDevicetypename()))) {
            predicates.add(criteriaBuilder.equal(root.get("deviceType").get("name"), stocktakingSearchForm.getDevicetypename()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getEmployeenumbersap()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("numbersap"), stocktakingSearchForm.getEmployeenumbersap()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getEmployeefirstname()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("firstname"), stocktakingSearchForm.getEmployeefirstname()));
        }
        if (!Strings.isNullOrEmpty(String.valueOf(stocktakingSearchForm.getEmployeelastname()))) {
            predicates.add(criteriaBuilder.equal(root.get("employee").get("lastname"), stocktakingSearchForm.getEmployeelastname()));
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
