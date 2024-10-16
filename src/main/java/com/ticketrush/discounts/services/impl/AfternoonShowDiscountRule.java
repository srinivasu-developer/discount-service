package com.ticketrush.discounts.services.impl;

import com.ticketrush.discounts.services.DiscountRule;

import java.time.LocalDateTime;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
public class AfternoonShowDiscountRule implements DiscountRule {

    @Override
    public boolean isApplicable(DiscountRequest request) {
        LocalDateTime showTime = request.getShowTime();
        return showTime.getHour() >= 12 && showTime.getHour() < 16;
    }

    @Override
    public double applyDiscount(DiscountRequest request) {
        double totalPrice = request.getTickets().stream()
                .mapToDouble(Ticket::getPrice).sum();
        return totalPrice * (discountEntity.getPercentage / 100);
    }
}
