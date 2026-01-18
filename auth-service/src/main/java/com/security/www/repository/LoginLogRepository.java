package com.security.www.repository;

import com.security.www.entity.LoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginLogRepository
        extends JpaRepository<LoginLogEntity, String> {
}
