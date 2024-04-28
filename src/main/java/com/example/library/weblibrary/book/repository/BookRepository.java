package com.example.library.weblibrary.book.repository;

import com.example.library.weblibrary.book.entities.Book;
import com.example.library.weblibrary.book.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByCategory(Category category);
}
