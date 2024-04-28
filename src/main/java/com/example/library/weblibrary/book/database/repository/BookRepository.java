package com.example.library.weblibrary.book.database.repository;

import com.example.library.weblibrary.book.database.entity.BookEntity;
import com.example.library.weblibrary.book.dto.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByTitle(String title);

    List<BookEntity> findBookByAuthor(String author);

    List<BookEntity> findBookByCategory(Category category);
}
