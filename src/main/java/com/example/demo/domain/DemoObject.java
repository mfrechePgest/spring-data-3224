package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "DEMO")
@SequenceGenerator(name = "SEQ_DEMO_ID", allocationSize = 10, sequenceName = "SEQ_DEMO_ID")
public class DemoObject {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEMO_ID")
    private Long id;

    @Column
    private LocalDateTime creationDate;

}
