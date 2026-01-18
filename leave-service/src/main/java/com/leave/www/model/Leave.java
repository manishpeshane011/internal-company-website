package com.leave.www.model;

import com.common.www.model.BaseEntity;
import com.common.www.model.User;
import com.leave.www.enums.LeaveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import com.common.www.model.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "leaves")
public class Leave extends BaseEntity {

    /* Employee who applied for leave (from Auth/User service) */
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    /* Manager/Admin who approved the leave */
    @Column(name = "approved_by_id")
    private Long approvedById;

    @Column(name = "leave_type", nullable = false, length = 50)
    private String leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(length = 500)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LeaveStatus status = LeaveStatus.PENDING;

    @Column(length = 500)
    private String comments;

    /* -------------------- Derived Field -------------------- */

    @Transient
    public long getDuration() {
        if (startDate == null || endDate == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    /* -------------------- Getters -------------------- */

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getApprovedById() {
        return approvedById;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public String getComments() {
        return comments;
    }

    /* -------------------- Setters -------------------- */

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setApprovedById(Long approvedById) {
        this.approvedById = approvedById;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

