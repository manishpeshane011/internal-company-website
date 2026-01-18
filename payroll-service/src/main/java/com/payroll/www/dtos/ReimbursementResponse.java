package com.payroll.www.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReimbursementResponse {
    private Long id;
    private LocalDate date;
    private String category;
    private BigDecimal amount;
    private BigDecimal approvedAmount;
    private String status;
}
