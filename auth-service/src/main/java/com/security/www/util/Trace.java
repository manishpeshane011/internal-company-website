package com.security.www.util;


import org.slf4j.MDC;

public class Trace {
    public static String id() {
        String v = MDC.get("traceId");
        return v != null ? v : "NA";
    }
}
