package com.ticketrush.discounts.services.impl;

import com.ticketrush.discounts.entities.DiscountEntity;
import com.ticketrush.discounts.repositories.DiscountRepository;
import com.ticketrush.discounts.services.DiscountRule;
import com.ticketrush.discounts.services.DiscountRuleManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: srinivasun
 * @Since: 16/10/24
 */
public class DiscountRuleManagerImpl implements DiscountRuleManager {

    private DiscountRepository discountRepository;

    @Autowired
    public DiscountRuleManagerImpl(final DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public DiscountResponse applyBestDiscount(DiscountRequest request) {
        List<DiscountRule> discountRules = findDiscountRules();
        DiscountRule bestRule = discountRules.stream()
                .filter(rule -> rule.isApplicable(request)).min(Comparator.comparingInt(DiscountRule::getPriority))
                .orElse(null);
        if (bestRule != null) {
            double discount = bestRule.applyDiscount(request);
            return new DiscountResponse(discount);
        }
        return new DiscountResponse(0);
    }



    private List<DiscountRule> findDiscountRules() {
        // we can cache these results, since adding new discounts is not so frequent
        List<DiscountEntity> activeDiscounts = discountRepository.findByIsActiveTrueAndBetweenStartDateAndEndDate(LocalDate.now());
        // TODO: Based on rule have to create different rules, this needs Drools integration to make it effective
        List<DiscountRule> discountRules = new ArrayList<>();
        discountRules.add(new ThirdTicketDiscountRule(activeDiscounts.get(0).getPriority()));
        discountRules.add(new AfternoonShowDiscountRule(activeDiscounts.get(1).getPriority()));
        return discountRules;
    }
}
