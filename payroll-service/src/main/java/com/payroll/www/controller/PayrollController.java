package com.payroll.www.controller;

import com.payroll.www.dtos.ReimbursementRequest;
import com.payroll.www.dtos.SalaryRequest;
import com.payroll.www.dtos.TaxDeclarationRequest;
import com.payroll.www.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/reimbursements")
    public ResponseEntity<?> reimbursements() {
        return ResponseEntity.ok(payrollService.getReimbursements());
    }

    @PostMapping("/reimbursements")
    public ResponseEntity<?> createReimbursement(@RequestBody ReimbursementRequest req) {
        payrollService.createReimbursement(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/payslips")
    public ResponseEntity<?> payslips(@RequestParam int year) {
        return ResponseEntity.ok(payrollService.getPayslips(year));
    }

    @PostMapping("/generate-payslip")
    public ResponseEntity<?> generatePayslip() {
        return ResponseEntity.ok(payrollService.generatePayslip());
    }

    @GetMapping("/tax-summary")
    public ResponseEntity<?> taxSummary() {
        return ResponseEntity.ok(payrollService.taxSummary());
    }

    @PostMapping("/tax-declaration")
    public ResponseEntity<?> taxDeclaration(@RequestBody TaxDeclarationRequest req) {
        payrollService.submitTax(req);
        return ResponseEntity.ok("Tax declared");
    }

    @PostMapping("/salary-calculator")
    public ResponseEntity<?> salaryCalculator(@RequestBody SalaryRequest req) {
        return ResponseEntity.ok(payrollService.calculateSalary(req));
    }
}
