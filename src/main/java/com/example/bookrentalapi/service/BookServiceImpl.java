package com.example.bookrentalapi.service;

import com.example.bookrentalapi.model.Book;
import com.example.bookrentalapi.repository.BookRepository;
import com.example.bookrentalapi.service.contract.BookService;
import com.example.bookrentalapi.service.specification.Filter;
import com.example.bookrentalapi.service.specification.BookSearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book getBookById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public Page<Book> getBooksByParameters(String udk, String title, String author, int page, int size) {
        List<Filter> filters = new ArrayList<>();
        if (udk != null) {
            filters.add(new Filter("udk", udk));
        }
        if (title != null) {
            filters.add(new Filter("title", title));
        }
        if (author != null) {
            filters.add(new Filter("author", author));
        }

        BookSearchSpecification specification = new BookSearchSpecification(filters);

        Pageable paging = PageRequest.of(page, size);
        return bookRepository.findAll(specification, paging);
    }

    @Transactional
    @Override
    public Page<Book> getBooksRentedOutByUserId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bookRepository.getBooksRentedOutByUserId(id, paging);
    }

    @Transactional
    @Override
    public Page<Book> getBooksRentedByUserId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bookRepository.getBooksRentedByUserId(id, paging);
    }

    @Override
    public Page<Book> getOverdueBooksRentedOutByUserId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        LocalDateTime date = LocalDateTime.now();
        return bookRepository.getOverdueBooksRentedOutByUserId(id, java.sql.Timestamp.valueOf(date), paging);
    }

    @Override
    public Page<Book> getOverdueBooksRentedByUserId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        LocalDateTime date = LocalDateTime.now();
        return bookRepository.getOverdueBooksRentedByUserId(id, java.sql.Timestamp.valueOf(date), paging);
    }

    @Transactional
    @Override
    public Page<Book> getBooks(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bookRepository.findAll(paging);
    }

    @Override
    public Page<Book> getBooksByOwnerId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bookRepository.getBooksByOwnerId(id, paging);
    }

    @Transactional
    @Override
    public Book updateBook(Long id, Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setActive(newBook.isActive());
                    book.setUdk(newBook.getUdk());
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setPeriod(newBook.getPeriod());
                    book.setNotes(newBook.getNotes());
                    book.setImage(newBook.getImage());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> bookRepository.save(newBook));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

    @Override
    public void deleteAllBooksByOwnerId(Long id) {
        bookRepository.deleteAll(bookRepository.getBooksByOwnerId(id));
    }
}
