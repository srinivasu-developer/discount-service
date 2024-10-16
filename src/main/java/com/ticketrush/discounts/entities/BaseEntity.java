package com.ticketrush.discounts.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Author: srinivasun
 * Since: 16/10/24
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdBy = ""; //TODO: needs to get user from context
    }

    @PreUpdate
    public void preUpdate() {
        updatedBy = ""; // TODO: needs to get user from context
    }

}
