package com.example.bookrentalapi.repository;

import com.example.bookrentalapi.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> getBooksByOwnerId(Long id);
    Page<Book> getBooksByOwnerId(Long id, Pageable pageable);
    @Query(value = "select r.book from Rental r where r.book.owner.id = ?1")
    Page<Book> getBooksRentedOutByUserId(Long id, Pageable pageable);

    @Query(value = "select r.book from Rental r where r.renter.id = ?1")
    Page<Book> getBooksRentedByUserId(Long id, Pageable pageable);

    @Query(value = "select r.book from Rental r where " +
            "r.book.owner.id = ?1 and " +
            "r.rentalEnds < ?2")
    Page<Book> getOverdueBooksRentedOutByUserId(Long id, Date date, Pageable pageable);

    @Query(value = "select r.book from Rental r where " +
            "r.renter.id = ?1 and " +
            "r.rentalEnds < ?2")
    Page<Book> getOverdueBooksRentedByUserId(Long id, Date date, Pageable pageable);
}
