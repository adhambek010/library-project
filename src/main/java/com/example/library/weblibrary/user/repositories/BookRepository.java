package com.example.library.weblibrary.user.repositories;

import com.example.library.weblibrary.user.entities.Book;
import com.example.library.weblibrary.user.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByCategory(Category category);
}
