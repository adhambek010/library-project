package com.example.library.weblibrary.book.dto;

import com.example.library.weblibrary.book.dto.enums.Category;
import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private boolean available;
    private Category category;
}
