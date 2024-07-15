package com.example.demo.repository;

import com.example.demo.domain.DemoObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DemoRepository
        extends JpaRepository<DemoObject, Long>, JpaSpecificationExecutor<DemoObject> {
}
