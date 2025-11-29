package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String repository;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String location;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
