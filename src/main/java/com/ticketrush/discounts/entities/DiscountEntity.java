package com.ticketrush.discounts.entities;

import com.ticketrush.discounts.enums.DiscountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
@Getter
@Setter
@Entity
@Table(name = "movies")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class DiscountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority")
    private int priority;

    @Column(name = "percentage")
    private double percentage;

    @Column(name = "priority")
    private String condition;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DiscountType type;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "start_date", columnDefinition = "DATE", nullable = false)
    private Date startDate;

    @Column(name = "end_date", columnDefinition = "DATE", nullable = false)
    private Date endDate;

}
