package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import java.time.LocalDateTime;

@Entity
@Table(name = "technologies")
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "project_id")
    private Long projectID;

    @Column(nullable = false)
    private String technology;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
