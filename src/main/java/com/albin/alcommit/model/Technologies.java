package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "technologies")
@Getter
@Setter
public class Technologies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "project_id")
    private Long projectID;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String technology;

    @Column(name = "created_by")
    private Long createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private Long updateBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
