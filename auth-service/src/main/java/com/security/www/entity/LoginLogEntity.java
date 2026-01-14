package com.security.www.entity;
import java.util.UUID;

import com.common.www.model.BaseUuidEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "login_logs",
       indexes = {
           @Index(name="idx_login_user", columnList="userId"),
           @Index(name="idx_login_method", columnList="loginMethod")
       })
public class LoginLogEntity extends BaseUuidEntity {

    @Column(nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(length = 45)
    private String ipAddress;

    @Lob
    private String userAgent;

    @Column(nullable = false, length = 50)
    private String loginMethod = "password";
}
