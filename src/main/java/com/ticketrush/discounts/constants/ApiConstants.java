package com.ticketrush.discounts.constants;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
public final class ApiConstants {

    private ApiConstants() {}

    public static final String API_PREFIX = "/api";
    public static final String API_VERSION_V1 = "/v1";
    public static final String DISCOUNTS_API_NORMAL = API_PREFIX + API_VERSION_V1 + "/discounts";
    public static final String DISCOUNTS_API_ADMIN = DISCOUNTS_API_NORMAL + "/admin";
    public static final String DISCOUNTS_API_ONBOARD = "/discount";
    public static final String DISCOUNTS_API_SINGLE = DISCOUNTS_API_ADMIN + "/single-discount";
    public static final String DISCOUNTS_API_MULTI = DISCOUNTS_API_ADMIN + "/multi-discount";

}
