package com.notification.www.model;

import com.common.www.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User employee;

    private String name;
    private String documentType;
    private String filePath;

    private String description;
    private Long size;
    private Instant uploadedAt = Instant.now();
}
