package com.ticketrush.discounts.services.impl;

import com.ticketrush.discounts.services.DiscountRule;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
public class ThirdTicketDiscountRule implements DiscountRule {

    private final int priority;

    public ThirdTicketDiscountRule(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean isApplicable(DiscountRequest request) {
        return request.getTickets().size() >= 3;
    }

    @Override
    public double applyDiscount(DiscountRequest request) {
        double thirdTicketPrice = request.getTickets().get(2).getPrice();
        return thirdTicketPrice * (discountEntity.getPercentage / 100);
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
