package com.example.bookrentalapi.dto.mapper.contract;

import java.util.List;

public interface BaseMapper<T, L> {
    T toDto(L user);
    List<T> toDto(List<L> users);
}
