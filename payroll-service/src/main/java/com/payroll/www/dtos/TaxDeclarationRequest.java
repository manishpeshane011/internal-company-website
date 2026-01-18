package com.payroll.www.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxDeclarationRequest {
    private BigDecimal section80C;
    private BigDecimal section80D;

    public BigDecimal getSection80C() {
        return section80C;
    }

    public void setSection80C(BigDecimal section80C) {
        this.section80C = section80C;
    }

    public BigDecimal getSection80D() {
        return section80D;
    }

    public void setSection80D(BigDecimal section80D) {
        this.section80D = section80D;
    }
}
