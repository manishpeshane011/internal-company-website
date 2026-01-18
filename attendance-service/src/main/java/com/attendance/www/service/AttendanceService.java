package com.attendance.www.service;


import com.attendance.www.dtos.AttendanceResponseDTO;
import com.attendance.www.dtos.MonthlyAttendanceDTO;

public interface AttendanceService {

    void checkIn();

    void checkOut();

    AttendanceResponseDTO getTodayAttendance();

    MonthlyAttendanceDTO getMonthlyAttendance(int month, int year);
}
