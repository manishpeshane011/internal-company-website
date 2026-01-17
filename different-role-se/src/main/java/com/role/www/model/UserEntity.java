package com.role.www.model;

import java.time.LocalDate;
import java.util.UUID;

import com.common.www.entity.UserType;
import com.common.www.model.BaseUuidEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "users",
        indexes = {
                @Index(name="idx_user_username", columnList="username", unique = true),
                @Index(name="idx_user_email", columnList="email", unique = true),
                @Index(name="idx_user_employee_id", columnList="employeeId", unique = true),
                @Index(name="idx_user_reporting_to", columnList="reportingToId")
        })
public class UserEntity  extends BaseUuidEntity {

    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    // store password in auth-service ideally; kept here only if you want monolithic identity
    @Column(nullable = false)
    private String passwordHash;

    @Column(length = 150)
    private String firstName;

    @Column(length = 150)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserType userType = UserType.EMPLOYEE;

    @Column(unique = true, length = 50)
    private String employeeId;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String department;

    @Column(length = 100)
    private String designation;

    private LocalDate dateOfJoining;

    @Column(length = 700)
    private String profileImagePath;

    @Lob
    private String address;

    @Column(length = 20)
    private String emergencyContact;

    // Self hierarchy: just store ID
    @Column(columnDefinition = "uuid")
    private UUID reportingToId;

    @Column(nullable = false)
    private boolean active = true;
}
