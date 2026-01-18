package com.payroll.www.service;

import com.payroll.www.dtos.*;

import java.util.List;

public interface PayrollService {

    List<ReimbursementResponse> getReimbursements();

    void createReimbursement(ReimbursementRequest request);

    List<PayslipResponse> getPayslips(int year);

    PayslipResponse generatePayslip();

    TaxSummaryResponse taxSummary();

    void submitTax(TaxDeclarationRequest request);

    SalaryResponse calculateSalary(SalaryRequest request);
}

