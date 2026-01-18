package com.common.www.exception;

public class AttendanceNotFoundException extends RuntimeException {
    public AttendanceNotFoundException(String msg) {
        super(msg);
    }
}
