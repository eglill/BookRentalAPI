package com.example.bookrentalapi.controller.contract;

import com.example.bookrentalapi.controller.ResponsePage;
import com.example.bookrentalapi.dto.BookDTO;
import com.example.bookrentalapi.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BookController {
    ResponseEntity<BookDTO> addBook(Book book);
    ResponseEntity<BookDTO> getBookById(Long id);
    ResponseEntity<ResponsePage<BookDTO>> getBooksByOwnerId(Long id, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getBooksByParameters(String udk, String title, String author, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getBooksRentedOutByUserId(Long id, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getBooksRentedByUserId(Long id, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getOverdueBooksRentedOutByUserId(Long id, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getOverdueBooksRentedByUserId(Long id, int page, int size);
    ResponseEntity<ResponsePage<BookDTO>> getBooks(int page, int size);
    ResponseEntity<BookDTO> updateBook(Long id, Book book);
    ResponseEntity<HttpStatus> deleteBook(Long id);
    ResponseEntity<HttpStatus> deleteAllBooks();
    ResponseEntity<HttpStatus> deleteAllBooksByOwnerId(Long id);
}
