package com.security.www.entity;
import java.util.UUID;

import com.common.www.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "login_logs",
        indexes = {
                @Index(name = "idx_login_user", columnList = "user_id"),
                @Index(name = "idx_login_method", columnList = "login_method")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLogEntity extends BaseEntity {

    // ❌ DO NOT use UUID with columnDefinition=uuid for MySQL
    // ✅ Use String for cross-service reference
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Lob
    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "login_method", nullable = false, length = 50)
    private String loginMethod;
}

