package com.example.library.weblibrary.user.entities;

import com.example.library.weblibrary.user.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private Category category;
    // TODO kitobni maktab bilan bog'lash kerakmi
}
