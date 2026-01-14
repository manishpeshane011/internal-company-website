package com.attendance.www.model;

import com.common.www.enums.AttendanceStatus;
import com.common.www.model.BaseEntity;
import com.common.www.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "date"})
})
@Getter
@Setter
public class Attendance extends BaseEntity {

    @ManyToOne
    private User employee;

    private LocalDate date;

    private Instant loginTime;
    private Instant logoutTime;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status = AttendanceStatus.ABSENT;

    private Double workingHours = 0.0;
    private String notes;
}
