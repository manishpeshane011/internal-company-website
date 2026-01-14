package com.common.www.model;

import com.common.www.enums.UserType;
import com.common.www.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.EMPLOYEE;

    @Column(unique = true)
    private String employeeId;

    private String phone;
    private String department;
    private String designation;
    private LocalDate dateOfJoining;

    private String profileImage;
    private String address;
    private String emergencyContact;

    @ManyToOne
    @JoinColumn(name = "reporting_to")
    private User reportingTo;

    @OneToMany(mappedBy = "reportingTo")
    private List<User> subordinates = new ArrayList<>();
}
