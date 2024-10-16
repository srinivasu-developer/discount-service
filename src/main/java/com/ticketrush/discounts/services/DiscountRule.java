package com.ticketrush.discounts.services;

import com.ticketrush.discounts.entities.DiscountEntity;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
public interface DiscountRule {

    boolean isApplicable(DiscountRequest request);

    double applyDiscount(DiscountRequest request);

    int getPriority();

}
