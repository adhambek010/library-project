package com.example.library.weblibrary.repositories;

import com.example.library.weblibrary.entities.Book;
import com.example.library.weblibrary.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByCategory(Category category);
}
