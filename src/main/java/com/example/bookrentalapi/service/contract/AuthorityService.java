package com.example.bookrentalapi.service.contract;


import com.example.bookrentalapi.model.Authority;
import com.example.bookrentalapi.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorityService {
    Authority addNewAuthority(Authority authority);
    Authority getAuthorityById(Long id);
    Page<Authority> getAuthorities(int page, int size);
    Authority updateAuthority(Long id, Authority authority);
    void deleteAuthority(Long id);
    void deleteAllAuthorities();
}
