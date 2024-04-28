package com.example.library.weblibrary.school.entities;

import com.example.library.weblibrary.book.database.entity.BookEntity;
import com.example.library.weblibrary.user.database.entities.BaseEntity;
import com.example.library.weblibrary.user.database.entities.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school")
public class SchoolEntity extends BaseEntity {
    private int schoolNumber;
    private String region;
    private String distinct;
    @ManyToOne
    @JoinColumn(name = "user_identifier")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "book_identifier")
    private BookEntity book;
}
