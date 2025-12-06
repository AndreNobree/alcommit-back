package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
@Getter
@Setter
public class Commits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "project_id")
    private Long projectId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String branch;

    @Column(columnDefinition = "TEXT")
    private String token;

    @Column(name = "created_by")
    private Long createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private Long updateBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

}
