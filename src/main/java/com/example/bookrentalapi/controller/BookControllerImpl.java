package com.example.bookrentalapi.controller;

import com.example.bookrentalapi.controller.contract.BookController;
import com.example.bookrentalapi.dto.BookDTO;
import com.example.bookrentalapi.dto.mapper.BookMapper;
import com.example.bookrentalapi.model.Book;
import com.example.bookrentalapi.service.BookServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/book")
public class BookControllerImpl implements BookController {

    private final BookServiceImpl bookService;
    private final BookMapper bookMapper;
    private final ApiResponseBuilder<BookDTO, Book> response;

    @Override
    @PostMapping
    public ResponseEntity<BookDTO> addBook(
            @Parameter(description = "Book to be added")
            @RequestBody Book book) {
        return response.getResponseEntity(
                bookMapper.toDto(bookService.addBook(book)),
                HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> getBookById(
            @Parameter(description = "Id of book to be searched")
            @PathVariable Long id) {
        return response.getResponseEntity(
                bookMapper.toDto(bookService.getBookById(id)),
                HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<ResponsePage<BookDTO>> getBooksByOwnerId(
            @Parameter(description = "Id of user")
            @PathVariable Long id,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getBooksByOwnerId(id, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/search")
    public ResponseEntity<ResponsePage<BookDTO>> getBooksByParameters(
            @Parameter(description = "Universal decimal classification for books")
            @RequestParam(required = false) String udk,
            @Parameter(description = "Book title")
            @RequestParam(required = false) String title,
            @Parameter(description = "Book author")
            @RequestParam(required = false) String author,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getBooksByParameters(udk, title, author, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/rented-out/{id}")
    public ResponseEntity<ResponsePage<BookDTO>> getBooksRentedOutByUserId(
            @Parameter(description = "User id")
            @PathVariable Long id,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getBooksRentedOutByUserId(id, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/rented/{id}")
    public ResponseEntity<ResponsePage<BookDTO>> getBooksRentedByUserId(
            @Parameter(description = "User id")
            @PathVariable Long id,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getBooksRentedByUserId(id, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/rented-out-overdue/{id}")
    public ResponseEntity<ResponsePage<BookDTO>> getOverdueBooksRentedOutByUserId(
            @Parameter(description = "User id")
            @PathVariable Long id,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getOverdueBooksRentedOutByUserId(id, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/rented-overdue/{id}")
    public ResponseEntity<ResponsePage<BookDTO>> getOverdueBooksRentedByUserId(
            @Parameter(description = "User id")
            @PathVariable Long id,
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getOverdueBooksRentedByUserId(id, page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponsePage<BookDTO>> getBooks(
            @Parameter(description = "Page number")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items on page")
            @RequestParam(defaultValue = "10") int size) {
        Page<Book> pages = bookService.getBooks(page, size);
        List<BookDTO> books = bookMapper.toDto(pages.getContent());
        return response.getResponseEntity(books, pages, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @Parameter(description = "Book id")
            @PathVariable Long id,
            @Parameter(description = "New Book with changed fields")
            @RequestBody Book book) {
        return response.getResponseEntity(
                bookMapper.toDto(bookService.updateBook(id, book)),
                HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteBook(
            @Parameter(description = "Book id")
            @PathVariable Long id) {
        bookService.deleteBook(id);
        return response.getResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        bookService.deleteAllBooks();
        return response.getResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatus> deleteAllBooksByOwnerId(
            @Parameter(description = "User id")
            @PathVariable Long id) {
        bookService.deleteAllBooksByOwnerId(id);
        return response.getResponseEntity(HttpStatus.OK);
    }
}
