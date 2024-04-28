package com.example.library.weblibrary.user.database.entities;

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
@Table(name = "school")
public class School extends BaseEntity{
    private String region;
    private String district;
    private int schoolNumber;

    private int numberOfStudents;
}
