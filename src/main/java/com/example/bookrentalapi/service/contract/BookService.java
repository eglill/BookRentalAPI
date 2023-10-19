package com.example.bookrentalapi.service.contract;

import com.example.bookrentalapi.model.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(Long id);
    Page<Book> getBooksByOwnerId(Long id, int page, int size);
    Page<Book> getBooksByParameters(String udk, String title, String author, int page, int size);
    Page<Book> getBooksRentedOutByUserId(Long id, int page, int size);
    Page<Book> getBooksRentedByUserId(Long id, int page, int size);
    Page<Book> getOverdueBooksRentedOutByUserId(Long id, int page, int size);
    Page<Book> getOverdueBooksRentedByUserId(Long id, int page, int size);
    Page<Book> getBooks(int page, int size);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    void deleteAllBooks();
    void deleteAllBooksByOwnerId(Long id);
}
