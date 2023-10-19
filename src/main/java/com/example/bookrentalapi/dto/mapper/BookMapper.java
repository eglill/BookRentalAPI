package com.example.bookrentalapi.dto.mapper;

import com.example.bookrentalapi.dto.BookDTO;
import com.example.bookrentalapi.dto.mapper.contract.BaseMapper;
import com.example.bookrentalapi.model.Book;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class BookMapper implements BaseMapper<BookDTO, Book> {

    ModelMapper modelMapper;

    @Autowired
    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public BookDTO toDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Transactional
    public List<BookDTO> toDto(List<Book> books) {
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }
}