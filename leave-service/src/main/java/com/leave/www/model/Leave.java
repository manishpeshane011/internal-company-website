package com.leave.www.model;

import com.common.www.model.BaseEntity;
import com.common.www.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
public class Leave extends BaseEntity {

    @ManyToOne
    private User employee;

    private String leaveType;

    private LocalDate startDate;
    private LocalDate endDate;

    private String reason;

    private String status = "PENDING";

    @ManyToOne
    private User approvedBy;

    private String comments;

    @Transient
    public long getDuration() {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
