package com.attendance.www.dtos;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class MonthlyAttendanceDTO {

    private int month;
    private int year;
    private List<AttendanceResponseDTO> records;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<AttendanceResponseDTO> getRecords() {
        return records;
    }

    public void setRecords(List<AttendanceResponseDTO> records) {
        this.records = records;
    }

    public MonthlyAttendanceDTO() {
    }
}
