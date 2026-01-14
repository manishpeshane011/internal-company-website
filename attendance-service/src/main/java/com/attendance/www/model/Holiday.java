package com.attendance.www.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private LocalDate date;
    private String description;
    private Boolean recurring = false;
}
