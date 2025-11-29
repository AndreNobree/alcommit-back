package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
public class Commits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "project_id")
    private Long projectID;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String branch;

    @Column(columnDefinition = "TEXT")
    private String token;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
