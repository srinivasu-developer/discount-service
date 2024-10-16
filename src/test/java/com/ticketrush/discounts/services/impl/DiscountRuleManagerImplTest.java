package com.ticketrush.discounts.services.impl;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
import com.ticketrush.discounts.repositories.DiscountRepository;
import com.ticketrush.discounts.services.DiscountRuleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DiscountRuleManagerImplTest {

    private DiscountRuleManager discountRuleManager;

    @Mock
    private DiscountRepository discountRepository;

    @BeforeEach
    public void setUp() {
        discountRuleManager = new DiscountRuleManagerImpl(discountRepository);
    }

    @Test
    public void testThirdTicketDiscountApplied() {
        DiscountRequest request = new DiscountRequest(
                "123",
                "Hyderabad",
                LocalDateTime.of(2024, 10, 8, 10, 0), // Not an afternoon show
                Arrays.asList(new Ticket("Normal", 200), new Ticket("Normal", 200), new Ticket("Normal", 200))
        );

        DiscountResponse response = discountRuleManager.applyBestDiscount(request);

        assertEquals(100, response.getDiscountDetails().getDiscountAmount());
        assertEquals(500, response.getDiscountedTotal());
    }

    @Test
    public void testAfternoonShowDiscountApplied() {
        DiscountRequest request = new DiscountRequest(
                "123",
                "Hyderabad",
                LocalDateTime.of(2024, 10, 8, 14, 0), // Afternoon show
                Arrays.asList(new Ticket("Normal", 200), new Ticket("Normal", 200))
        );

        DiscountResponse response = discountRuleManager.applyBestDiscount(request);

        assertEquals(80, response.getDiscountDetails().getDiscountAmount()); // 20% off
        assertEquals(320, response.getDiscountedTotal());
    }

    @Test
    public void testNoDiscountApplied() {

        DiscountRequest request = new DiscountRequest(
                "123",
                "Hyderabad",
                LocalDateTime.of(2024, 10, 8, 10, 0), // Not an afternoon show
                Arrays.asList(new Ticket("Normal", 200), new Ticket("Normal", 200)) // Only 2 tickets
        );

        DiscountResponse response = discountRuleManager.applyBestDiscount(request);

        assertEquals(0, response.getDiscountDetails().getDiscountAmount());
        assertEquals(400, response.getDiscountedTotal());
    }

    @Test
    public void testHigherPriorityRuleApplied() {
        DiscountRequest request = new DiscountRequest(
                "123",
                "Hyderabad",
                LocalDateTime.of(2024, 10, 8, 14, 0), // Afternoon show
                Arrays.asList(new Ticket("Normal", 200), new Ticket("Normal", 200), new Ticket("Normal", 200)) // 3 tickets
        );

        DiscountResponse response = discountRuleManager.applyBestDiscount(request);

        // Only one rule should be applied, third ticket discount should take precedence
        assertEquals(100, response.getDiscountDetails().getDiscountAmount()); // 50% off on third ticket
        assertEquals(500, response.getDiscountedTotal());
    }
}