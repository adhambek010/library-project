package com.example.library.weblibrary.school.entities;

import com.example.library.weblibrary.book.entities.Book;
import com.example.library.weblibrary.user.entities.BaseEntity;
import com.example.library.weblibrary.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class School extends BaseEntity {
    private int schoolNumber;
    private String region;
    private String distinct;
    @ManyToOne
    @JoinColumn(name = "user_identifier")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_identifier")
    private Book book;
}
