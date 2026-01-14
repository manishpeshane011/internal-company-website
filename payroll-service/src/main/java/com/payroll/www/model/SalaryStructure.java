package com.payroll.www.model;

import com.common.www.model.BaseEntity;
import com.common.www.model.User;
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

    @OneToOne
    private User employee;

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
}
