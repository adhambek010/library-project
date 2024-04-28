package com.example.library.weblibrary.book.entities;

import com.example.library.weblibrary.book.enums.BookStatus;
import com.example.library.weblibrary.book.enums.Category;
import com.example.library.weblibrary.user.entities.BaseEntity;
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
@Table(name = "book")
public class Book extends BaseEntity {
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
}
