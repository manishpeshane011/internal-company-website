package com.common.www.exception;

public class AlreadyCheckedOutException extends RuntimeException {
    public AlreadyCheckedOutException(String msg) {
        super(msg);
    }
}
