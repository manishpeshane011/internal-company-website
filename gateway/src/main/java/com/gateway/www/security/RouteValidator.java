package com.gateway.www.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouteValidator {

    private static final List<String> PUBLIC_URLS = List.of(
            "/api/auth/login",
            "/api/auth/register"
    );

    public boolean isSecured(HttpServletRequest request) {
        return PUBLIC_URLS.stream()
                .noneMatch(url -> request.getRequestURI().contains(url));
    }
}
