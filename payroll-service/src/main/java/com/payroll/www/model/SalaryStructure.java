package com.payroll.www.model;

import com.common.www.model.BaseEntity;
import com.common.www.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class SalaryStructure extends BaseEntity {

    /** Reference to User Service (NO JPA RELATION) */
    @Column(name = "employee_id", nullable = false, length = 36)
    private String employeeId;

    private BigDecimal basicSalary;
    private BigDecimal houseRentAllowance;
    private BigDecimal dearnessAllowance;
    private BigDecimal conveyanceAllowance;
    private BigDecimal medicalAllowance;
    private BigDecimal specialAllowance;

    private BigDecimal incentive;
    private BigDecimal bonus;
    private BigDecimal overtimePay;

    private BigDecimal employerPf;
    private BigDecimal employerEsi;
    private BigDecimal gratuity;

    private BigDecimal employeePf;
    private BigDecimal employeeEsi;
    private BigDecimal professionalTax;
    private BigDecimal tds;

    private BigDecimal leaveEncashment;
    private BigDecimal lopDeduction;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;

    private Boolean isActive = true;

    @Transient
    public BigDecimal getGrossSalary() {
        return basicSalary
                .add(houseRentAllowance)
                .add(dearnessAllowance)
                .add(conveyanceAllowance)
                .add(medicalAllowance)
                .add(specialAllowance)
                .add(incentive)
                .add(bonus)
                .add(overtimePay)
                .add(leaveEncashment);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getHouseRentAllowance() {
        return houseRentAllowance;
    }

    public void setHouseRentAllowance(BigDecimal houseRentAllowance) {
        this.houseRentAllowance = houseRentAllowance;
    }

    public BigDecimal getDearnessAllowance() {
        return dearnessAllowance;
    }

    public void setDearnessAllowance(BigDecimal dearnessAllowance) {
        this.dearnessAllowance = dearnessAllowance;
    }

    public BigDecimal getConveyanceAllowance() {
        return conveyanceAllowance;
    }

    public void setConveyanceAllowance(BigDecimal conveyanceAllowance) {
        this.conveyanceAllowance = conveyanceAllowance;
    }

    public BigDecimal getMedicalAllowance() {
        return medicalAllowance;
    }

    public void setMedicalAllowance(BigDecimal medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(BigDecimal specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public BigDecimal getIncentive() {
        return incentive;
    }

    public void setIncentive(BigDecimal incentive) {
        this.incentive = incentive;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(BigDecimal overtimePay) {
        this.overtimePay = overtimePay;
    }

    public BigDecimal getEmployerPf() {
        return employerPf;
    }

    public void setEmployerPf(BigDecimal employerPf) {
        this.employerPf = employerPf;
    }

    public BigDecimal getEmployerEsi() {
        return employerEsi;
    }

    public void setEmployerEsi(BigDecimal employerEsi) {
        this.employerEsi = employerEsi;
    }

    public BigDecimal getGratuity() {
        return gratuity;
    }

    public void setGratuity(BigDecimal gratuity) {
        this.gratuity = gratuity;
    }

    public BigDecimal getEmployeePf() {
        return employeePf;
    }

    public void setEmployeePf(BigDecimal employeePf) {
        this.employeePf = employeePf;
    }

    public BigDecimal getEmployeeEsi() {
        return employeeEsi;
    }

    public void setEmployeeEsi(BigDecimal employeeEsi) {
        this.employeeEsi = employeeEsi;
    }

    public BigDecimal getProfessionalTax() {
        return professionalTax;
    }

    public void setProfessionalTax(BigDecimal professionalTax) {
        this.professionalTax = professionalTax;
    }

    public BigDecimal getTds() {
        return tds;
    }

    public void setTds(BigDecimal tds) {
        this.tds = tds;
    }

    public BigDecimal getLeaveEncashment() {
        return leaveEncashment;
    }

    public void setLeaveEncashment(BigDecimal leaveEncashment) {
        this.leaveEncashment = leaveEncashment;
    }

    public BigDecimal getLopDeduction() {
        return lopDeduction;
    }

    public void setLopDeduction(BigDecimal lopDeduction) {
        this.lopDeduction = lopDeduction;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

