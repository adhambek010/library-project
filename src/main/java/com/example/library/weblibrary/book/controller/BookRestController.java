package com.example.library.weblibrary.book.controller;

import static com.example.library.weblibrary.config.endpoints.Endpoints.*;

import com.example.library.weblibrary.book.database.entity.BookEntity;
import com.example.library.weblibrary.book.dto.BookDTO;
import com.example.library.weblibrary.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookRestController {
    private final BookService bookService;

    /**
     * Retrieves all books.
     *
     * @return A list of all books.
     */
    @GetMapping(GET_ALL_BOOKS)
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to retrieve.
     * @return The book with the specified ID.
     */
    @GetMapping(GET_BOOK)
    public BookEntity getBook(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    /**
     * Retrieves the count of a book by its ID.
     *
     * @param id The ID of the book to retrieve the count for.
     * @return The quantity of the book with the specified ID.
     */
    @GetMapping(GET_BOOKS_COUNT)
    public int getBooksCount(@PathVariable String id) {
        return bookService.getBooksCount(id);
    }

    /**
     * Retrieves all available books.
     *
     * @return A list of available books.
     */
    @GetMapping(GET_AVAILABLE_BOOKS)
    public List<BookEntity> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    /**
     * Retrieves all borrowed books.
     *
     * @return A list of borrowed books.
     */
    @GetMapping(GET_BORROWED_BOOKS)
    public List<BookEntity> getBorrowedBooks() {
        return bookService.getBorrowedBooks();
    }

    //TODO: write method for checking reserved books
    //TODO: write method for returned books
    //TODO: connect book and student and write method getting books for specific userEntity

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to retrieve.
     * @return The book with the specified ID.
     */
    @GetMapping(GET_BOOKS_BY_ID)
    public BookEntity getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    /**
     * Retrieves books by title.
     *
     * @param title The title of the book(s) to retrieve.
     * @return A list of books with the specified title.
     */
    @GetMapping(GET_BOOKS_BY_TITLE)
    public List<BookEntity> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    /**
     * Retrieves books by author.
     *
     * @param author The author of the book(s) to retrieve.
     * @return A list of books by the specified author.
     */
    @GetMapping(GET_BOOK_BY_AUTHOR)
    public List<BookEntity> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

    /**
     * Retrieves books by character.
     *
     * @param character The character to search for in book titles.
     * @return A list of books containing the specified character in their titles.
     */
    @GetMapping(GET_BOOKS_BY_CHARACTER)
    public List<BookEntity> getBookByCharacter(@PathVariable String character) {
        return bookService.getBookByCharacter(character);
    }

    /**
     * Retrieves books by category.
     *
     * @param category The category of the book(s) to retrieve.
     * @return A list of books in the specified category.
     */
    @GetMapping(GET_BY_CATEGORY)
    public List<BookEntity> getBookByCategory(@PathVariable String category) {
        return bookService.getBookByCategory(category);
    }


    @PostMapping(ADD_BOOK)
    public void addBook(@RequestBody BookDTO book) {
        bookService.addBook(book);
    }

    //TODO: update book through patchMapping or putMapping


    @DeleteMapping(DELETE_BOOK)
    public void deleteBook(@PathVariable String  id) {
        bookService.deleteBook(id);
    }

}
