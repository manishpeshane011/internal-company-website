package com.task.www.model;

import com.common.www.enums.AttendanceStatus;
import com.common.www.model.BaseEntity;
import com.common.www.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Task extends BaseEntity {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    private User assignedBy;

    private String priority = "MEDIUM";
    private String status = "TODO";

    private LocalDate dueDate;
    private LocalDate completedDate;
}
