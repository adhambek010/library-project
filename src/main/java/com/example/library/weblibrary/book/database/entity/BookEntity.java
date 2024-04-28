package com.example.library.weblibrary.book.database.entity;

import com.example.library.weblibrary.book.dto.enums.BookStatus;
import com.example.library.weblibrary.user.database.entities.BaseEntity;
import com.example.library.weblibrary.book.dto.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
public class BookEntity extends BaseEntity {
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private boolean available;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private Instant crateDate = Instant.now();
}
