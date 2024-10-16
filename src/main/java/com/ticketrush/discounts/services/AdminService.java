package com.ticketrush.discounts.services;

/**
 * Holds all the services related to admin; like add, update, delete discounts in database
 *
 * Author: srinivasun
 * Since: 16/10/24
 */
public interface AdminService {

    DiscountDetails addDiscount(AdminRequest adminRequest);

    DiscountDetails updateDiscount(AdminRequest adminRequest);

    void deleteDiscount(long discountId);

}
