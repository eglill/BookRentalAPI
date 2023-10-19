package com.example.bookrentalapi.service.specification;

import com.example.bookrentalapi.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@AllArgsConstructor
public class BookSearchSpecification implements Specification<Book> {

    private final List<Filter> filters;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(criteriaBuilder.literal(Boolean.TRUE), Boolean.TRUE);

        for (Filter filter : this.filters) {
            String field = filter.getField();
            String value = ("%" + filter.getValue() + "%").toUpperCase();
            predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), value), predicate);
        }
        return predicate;
    }
}
