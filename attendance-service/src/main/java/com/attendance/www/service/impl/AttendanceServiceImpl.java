package com.attendance.www.service.impl;

import com.attendance.www.dtos.AttendanceResponseDTO;
import com.attendance.www.dtos.MonthlyAttendanceDTO;
import com.attendance.www.model.Attendance;
import com.attendance.www.repository.AttendanceRepository;
import com.attendance.www.service.AttendanceService;
import com.common.www.enums.AttendanceStatus;
import com.common.www.exception.AlreadyCheckedInException;
import com.common.www.exception.AlreadyCheckedOutException;
import com.common.www.exception.AttendanceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Slf4j
@Service
//@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private Long getLoggedInEmployeeId() {
        // Normally from SecurityContext / JWT
        return 1L;
    }

    @Override
    public void checkIn() {
        Long empId = getLoggedInEmployeeId();
        LocalDate today = LocalDate.now();

        attendanceRepository.findByEmployeeIdAndDate(empId, today)
                .ifPresent(a -> {
                    throw new AlreadyCheckedInException("Already checked in today");
                });

        Attendance attendance = new Attendance();
        attendance.setDate(today);
        attendance.setLoginTime(Instant.now());
        attendance.setStatus(AttendanceStatus.PRESENT);

        attendanceRepository.save(attendance);
        //log.info("Employee {} checked in", empId);
    }

    @Override
    public void checkOut() {
        Long empId = getLoggedInEmployeeId();
        LocalDate today = LocalDate.now();

        Attendance attendance = attendanceRepository
                .findByEmployeeIdAndDate(empId, today)
                .orElseThrow(() -> new AttendanceNotFoundException("No check-in found"));

        if (attendance.getLogoutTime() != null) {
            throw new AlreadyCheckedOutException("Already checked out");
        }

        attendance.setLogoutTime(Instant.now());

        double hours = Duration.between(
                attendance.getLoginTime(),
                attendance.getLogoutTime()
        ).toMinutes() / 60.0;

        attendance.setWorkingMinutes((long) hours);

        attendanceRepository.save(attendance);
        //log.info("Employee {} checked out", empId);
    }

    @Override
    public AttendanceResponseDTO getTodayAttendance() {
        Long empId = getLoggedInEmployeeId();
        LocalDate today = LocalDate.now();

        Attendance attendance = attendanceRepository
                .findByEmployeeIdAndDate(empId, today)
                .orElseThrow(() -> new AttendanceNotFoundException("Attendance not found"));

        return mapToDTO(attendance);
    }

    @Override
    public MonthlyAttendanceDTO getMonthlyAttendance(int month, int year) {
        Long empId = getLoggedInEmployeeId();

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<AttendanceResponseDTO> records =
                attendanceRepository.findByEmployeeIdAndDateBetween(empId, start, end)
                        .stream()
                        .map(this::mapToDTO)
                        .toList();

        MonthlyAttendanceDTO dto = new MonthlyAttendanceDTO();

        dto.setMonth(month);
        dto.setYear(year);
        dto.setRecords(records);

        return dto;

    }

    private AttendanceResponseDTO mapToDTO(Attendance a) {

        AttendanceResponseDTO dto = new AttendanceResponseDTO();

        dto.setDate(a.getDate());
        dto.setLoginTime(a.getLoginTime());
        dto.setLogoutTime(a.getLogoutTime());
        dto.setStatus(a.getStatus());
        dto.setWorkingHours(a.getWorkingHours());
        dto.setNotes(a.getNotes());

        return dto;
    }

}
