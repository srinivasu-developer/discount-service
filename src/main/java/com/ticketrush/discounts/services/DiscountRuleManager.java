package com.ticketrush.discounts.services;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
public interface DiscountRuleManager {

    DiscountResponse applyBestDiscount(DiscountRequest request);
}
