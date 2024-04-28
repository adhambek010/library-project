package com.example.library.weblibrary.user.entities;

import com.example.library.weblibrary.book.entities.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_books")
public class UserBooks extends BaseEntity {
    private int user_books_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_identifier")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_identifier")
    private Book book;
    private LocalDate borrowedDate;
    private LocalDate returnDate;


}
