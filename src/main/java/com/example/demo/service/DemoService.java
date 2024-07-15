package com.example.demo.service;

import com.example.demo.domain.DemoObject;
import com.example.demo.repository.DemoRepository;
import com.example.demo.repository.specification.DemoSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoService {

    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public Page<DemoObject> searchObjects(Pageable pageable) {
        log.info("Entering service method");
        Specification<DemoObject> specification = new DemoSpecification();
        return demoRepository.findAll(specification, pageable);
    }
}
