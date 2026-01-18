package com.payroll.www.repository;

import com.payroll.www.model.SalaryStructure;
import com.common.www.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryStructureRepository
        extends JpaRepository<SalaryStructure, Long> {

    Optional<SalaryStructure> findByEmployeeIdAndIsActiveTrue(String employeeId);
}

