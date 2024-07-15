package com.example.demo.repository.specification;

import com.example.demo.domain.DemoObject;
import com.example.demo.domain.DemoObject_;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.*;

@Slf4j
public class DemoSpecification
        implements Specification<DemoObject> {


    public DemoSpecification() {
    }

    @Override
    public Predicate toPredicate(@NonNull Root<DemoObject> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        // dumb filter
        final List<Predicate> filters = new ArrayList<>();
        filters.add(criteriaBuilder.gt(root.get(DemoObject_.ID), 0));

        return criteriaBuilder.and(filters.toArray(new Predicate[0]));
    }


}
