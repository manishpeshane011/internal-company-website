package com.payroll.www.service.impl;

import com.common.www.exception.ResourceNotFoundException;
import com.common.www.model.User;
import com.common.www.util.SecurityUtil;
import com.payroll.www.dtos.*;
import com.payroll.www.model.SalaryStructure;
import com.payroll.www.repository.SalaryStructureRepository;
import com.payroll.www.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {

    private static final Logger log = LoggerFactory.getLogger(PayrollServiceImpl.class);

    @Autowired
    private  SalaryStructureRepository salaryStructureRepository;

    // ---------------- REIMBURSEMENT ----------------

    @Override
    public List<ReimbursementResponse> getReimbursements() {
        log.info("Fetching reimbursements (demo)");
        return List.of(); // can be DB-backed later
    }



    @Override
    public void createReimbursement(ReimbursementRequest request) {
        log.info("Creating reimbursement for category {}", request.getCategory());
        // Persist later – demo logic
    }

    // ---------------- PAYSLIP ----------------

    @Override
    public List<PayslipResponse> getPayslips(int year) {
        log.info("Fetching payslips for year {}", year);
        return List.of(); // Typically generated monthly
    }

    @Override
    public PayslipResponse generatePayslip() {

        User user = SecurityUtil.getCurrentUser();
        SalaryStructure salary = getActiveSalary(user.getEmployeeId());

        BigDecimal gross = salary.getGrossSalary();
        BigDecimal deductions = salary.getEmployeePf()
                .add(salary.getEmployeeEsi())
                .add(salary.getProfessionalTax())
                .add(salary.getTds())
                .add(salary.getLopDeduction());

        PayslipResponse response = new PayslipResponse();
        response.setEmployeeId(response.getEmployeeId());
        response.setMonth(LocalDate.now().getMonthValue());
        response.setYear(LocalDate.now().getYear());
        response.setGrossSalary(gross);
        response.setTotalDeductions(deductions);
        response.setNetSalary(gross.subtract(deductions));
        response.setPaidDays(22);
        response.setWorkingDays(22);

        log.info("Payslip generated for user {}", user.getEmail());
        return response;
    }

    // ---------------- TAX ----------------

    @Override
    public TaxSummaryResponse taxSummary() {

        User user = SecurityUtil.getCurrentUser();
        SalaryStructure salary = getActiveSalary(user.getEmployeeId());

        TaxSummaryResponse response = new TaxSummaryResponse();
        response.setFinancialYear("2024-25");
        response.setTaxPaid(salary.getTds());
        response.setProjectedTax(salary.getTds().multiply(BigDecimal.valueOf(12)));
        response.setMonthlyTds(salary.getTds());

        return response;
    }



    @Override
    public void submitTax(TaxDeclarationRequest request) {

        if (request.getSection80C().compareTo(BigDecimal.valueOf(150000)) > 0) {
            throw new IllegalArgumentException("80C limit exceeded");
        }

        log.info("Tax declaration submitted");
    }

    // ---------------- SALARY CALCULATOR ----------------

    @Override
    public SalaryResponse calculateSalary(SalaryRequest request) {

        log.info("Calculating salary for CTC {}", request.getCtc());

        BigDecimal monthlyCtc = request.getCtc().divide(BigDecimal.valueOf(12), BigDecimal.ROUND_HALF_UP);

        BigDecimal basic = monthlyCtc.multiply(BigDecimal.valueOf(0.45));
        BigDecimal hra = basic.multiply(BigDecimal.valueOf(0.40));
        BigDecimal allowances = monthlyCtc.subtract(basic.add(hra));

        BigDecimal pf = basic.multiply(BigDecimal.valueOf(0.12));
        BigDecimal professionalTax = BigDecimal.valueOf(200);

        BigDecimal gross = basic.add(hra).add(allowances);
        BigDecimal deductions = pf.add(professionalTax);

        SalaryResponse response = new SalaryResponse();
        response.setGrossSalary(gross);
        response.setTotalDeductions(deductions);
        response.setNetSalary(gross.subtract(deductions));

        return response;
    }

    // ---------------- PRIVATE HELPERS ----------------

    private SalaryStructure getActiveSalary(String employeeId) {
        return salaryStructureRepository
                .findByEmployeeIdAndIsActiveTrue(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Salary structure not configured"));
    }
}
