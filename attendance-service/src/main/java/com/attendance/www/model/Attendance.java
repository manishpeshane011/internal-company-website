package com.attendance.www.model;

import com.common.www.enums.AttendanceStatus;
import com.common.www.model.BaseEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(
        name = "attendance",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "attendance_date"})
        }
)
public class Attendance extends BaseEntity {

    /* Employee ID from Auth/User service */
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate date;

    @Column(name = "login_time")
    private Instant loginTime;

    @Column(name = "logout_time")
    private Instant logoutTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AttendanceStatus status = AttendanceStatus.ABSENT;

    /* Stored in minutes for precision */
    @Column(name = "working_minutes")
    private Long workingMinutes = 0L;

    @Column(length = 500)
    private String notes;

    /* -------------------- Derived Field -------------------- */

    @Transient
    public Double getWorkingHours() {
        if (workingMinutes == null) return 0.0;
        return workingMinutes / 60.0;
    }

    /* -------------------- Getters -------------------- */

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public Instant getLogoutTime() {
        return logoutTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public Long getWorkingMinutes() {
        return workingMinutes;
    }

    public String getNotes() {
        return notes;
    }

    /* -------------------- Setters -------------------- */

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public void setLogoutTime(Instant logoutTime) {
        this.logoutTime = logoutTime;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public void setWorkingMinutes(Long workingMinutes) {
        this.workingMinutes = workingMinutes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /* -------------------- Utility -------------------- */

    public void calculateWorkingMinutes() {
        if (loginTime != null && logoutTime != null) {
            this.workingMinutes =
                    ChronoUnit.MINUTES.between(loginTime, logoutTime);
        }
    }
}
