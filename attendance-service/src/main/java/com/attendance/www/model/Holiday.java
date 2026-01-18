package com.attendance.www.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "holidays",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"holiday_date"})
        }
)
public class Holiday {

    @Id
    @GeneratedValue
    @Column(length = 36, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "holiday_date", nullable = false)
    private LocalDate date;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean recurring = false;

    /* -------------------- Lifecycle -------------------- */

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    /* -------------------- Getters -------------------- */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    /* -------------------- Setters -------------------- */

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }
}
