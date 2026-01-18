package com.attendance.www.controller;

import com.attendance.www.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('EMPLOYEE')")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn() {
        attendanceService.checkIn();
        return ResponseEntity.ok("Check-in successful");
    }

    @PostMapping("/check-out")
    public ResponseEntity<?> checkOut() {
        attendanceService.checkOut();
        return ResponseEntity.ok("Check-out successful");
    }

    @GetMapping("/today")
    public ResponseEntity<?> today() {
        return ResponseEntity.ok(attendanceService.getTodayAttendance());
    }

    @GetMapping("/monthly")
    public ResponseEntity<?> monthly(
            @RequestParam int month,
            @RequestParam int year) {
        return ResponseEntity.ok(
                attendanceService.getMonthlyAttendance(month, year)
        );
    }
}
