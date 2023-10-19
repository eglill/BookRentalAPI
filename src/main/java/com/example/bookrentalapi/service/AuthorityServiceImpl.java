package com.example.bookrentalapi.service;

import com.example.bookrentalapi.model.Authority;
import com.example.bookrentalapi.repository.AuthorityRepository;
import com.example.bookrentalapi.service.contract.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority addNewAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.getReferenceById(id);
    }

    @Override
    public Page<Authority> getAuthorities(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return authorityRepository.findAll(paging);
    }

    @Override
    public Authority updateAuthority(Long id, Authority newAuthority) {
        return authorityRepository.findById(id)
                .map(authority -> {
                    authority.setAuthority(newAuthority.getAuthority());
                    return authorityRepository.save(authority);
                })
                .orElseGet(() -> authorityRepository.save(newAuthority));
    }

    @Override
    public void deleteAuthority(Long id) {
        authorityRepository.deleteById(id);
    }

    @Override
    public void deleteAllAuthorities() {
        authorityRepository.deleteAll();
    }
}
