package com.payroll.www.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxSummaryResponse {
    private String financialYear;
    private BigDecimal taxPaid;
    private BigDecimal projectedTax;
    private BigDecimal monthlyTds;

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public BigDecimal getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(BigDecimal taxPaid) {
        this.taxPaid = taxPaid;
    }

    public BigDecimal getProjectedTax() {
        return projectedTax;
    }

    public void setProjectedTax(BigDecimal projectedTax) {
        this.projectedTax = projectedTax;
    }

    public BigDecimal getMonthlyTds() {
        return monthlyTds;
    }

    public void setMonthlyTds(BigDecimal monthlyTds) {
        this.monthlyTds = monthlyTds;
    }
}
