package com.payroll.www.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalaryRequest {
    private BigDecimal ctc;
    private String regime; // NEW / OLD
    private Integer lopDays;

    public BigDecimal getCtc() {
        return ctc;
    }

    public void setCtc(BigDecimal ctc) {
        this.ctc = ctc;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Integer getLopDays() {
        return lopDays;
    }

    public void setLopDays(Integer lopDays) {
        this.lopDays = lopDays;
    }
}
