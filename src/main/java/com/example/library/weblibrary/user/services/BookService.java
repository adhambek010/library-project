package com.example.library.weblibrary.user.services;

import com.example.library.weblibrary.user.entities.Book;
import com.example.library.weblibrary.exception.BookNotFoundException;
import com.example.library.weblibrary.user.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Retrieves all books from the repository.
     *
     * @return A list of all books.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID from the repository.
     *
     * @param id The ID of the book to retrieve.
     * @return The book with the specified ID.
     * @throws BookNotFoundException If no book with the specified ID is found.
     */
    public Book getBookById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }

    /**
     * Retrieves the quantity of a book by its ID from the repository.
     *
     * @param id The ID of the book to retrieve the count for.
     * @return The quantity of the book with the specified ID.
     * @throws BookNotFoundException If no book with the specified ID is found.
     */
    public int getBooksCount(String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get().getQuantity();
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }

    /**
     * Retrieves all available books from the repository.
     *
     * @return A list of available books.
     */
    public List<Book> getAvailableBooks() {
        var books = bookRepository.findAll();
        var availableBooks = new ArrayList<Book>();
        for (var book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    /**
     * Retrieves all borrowed books from the repository.
     *
     * @return A list of borrowed books.
     */
    public List<Book> getBorrowedBooks() {
        var books = bookRepository.findAll();
        var borrowedBooks = new ArrayList<Book>();
        for (var book : books) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    /**
     * Retrieves books by title from the repository.
     *
     * @param title The title of the book(s) to retrieve.
     * @return A list of books with the specified title.
     */
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    /**
     * Retrieves books by author from the repository.
     *
     * @param author The author of the book(s) to retrieve.
     * @return A list of books by the specified author.
     */
    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    /**
     * Retrieves books by character from the repository.
     *
     * @param character The character to search for in book titles.
     * @return A list of books containing the specified character in their titles.
     */
    public List<Book> getBookByCharacter(String character) {
        //return bookRepository.findBookByCharacter(character);
        //TODO: Implement a method to search books by character in title or delete this method.
        return null;
    }

    /**
     * Retrieves books by category from the repository.
     *
     * @param category The category of the book(s) to retrieve.
     * @return A list of books in the specified category.
     */
    public List<Book> getBookByCategory(String category) {
        //TODO: Implement a method to search books by category or delete this method.
        return null;
    }

    /**
     * Adds a book to the repository.
     *
     * @param book The book to add.
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Deletes a book from the repository by its ID.
     *
     * @param id The ID of the book to delete.
     */
    public void deleteBook(String  id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
    }

}
