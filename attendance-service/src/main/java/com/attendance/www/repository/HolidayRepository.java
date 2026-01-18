package com.attendance.www.repository;

import com.attendance.www.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface HolidayRepository extends JpaRepository<Holiday, UUID> {

    boolean existsByDate(LocalDate date);
}
