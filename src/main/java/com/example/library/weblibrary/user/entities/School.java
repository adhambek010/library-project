package com.example.library.weblibrary.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    private String region;
    private String district;
    private int schoolNumber;
    @ManyToOne
    private User user;

    private int numberOfStudents;
}
