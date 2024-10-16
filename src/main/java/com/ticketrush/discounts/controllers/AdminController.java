package com.ticketrush.discounts.controllers;

import com.ticketrush.discounts.repositories.DiscountRepository;
import com.ticketrush.discounts.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ticketrush.discounts.constants.ApiConstants.DISCOUNTS_API_ADMIN;
import static com.ticketrush.discounts.constants.ApiConstants.DISCOUNTS_API_ONBOARD;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
@RestController
@RequestMapping(DISCOUNTS_API_ADMIN)
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //Authorisation annotation to authorise the API
    @PostMapping(DISCOUNTS_API_ONBOARD)
    public ResponseEntity<DiscountDetails> create(@Validated AdminRequest adminRequest) {
        // TODO: validate the request
        DiscountDetails response = adminService.addDiscount(adminRequest);
        return ResponseEntity.created(response);
    }

    //Authorisation annotation to authorise the API
    @PostMapping(DISCOUNTS_API_ONBOARD)
    public ResponseEntity<DiscountDetails> update(@Validated AdminRequest adminRequest) {
        // TODO: validate the request
        DiscountDetails response = adminService.updateDiscount(adminRequest);
        return ResponseEntity.ok(response);
    }

    //Authorisation annotation to authorise the API
    @PostMapping(DISCOUNTS_API_ONBOARD)
    public ResponseEntity<DiscountDetails> delete(long discountId) {
        // TODO: soft delete the ID
        DiscountDetails response = adminService.updateDiscount(adminRequest);
        return ResponseEntity.ok(response);
    }
}
