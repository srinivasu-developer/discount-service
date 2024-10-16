package com.ticketrush.discounts.repositories;

import com.ticketrush.discounts.entities.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {

    /* Fetches all discounts which are active and current date between start and end date */
    List<DiscountEntity> findByIsActiveTrueAndBetweenStartDateAndEndDate(LocalDate currentDate);

}
