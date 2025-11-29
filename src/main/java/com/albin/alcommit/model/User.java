package com.albin.alcommit.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// desde o inicio eu já recebo o json e já igualo ao model.
//{
//        "email": "teste@mail.com",   =    private String email;
//        "username": "andre",         =    private String username;
//        "passwordHash": "abc123"     =    private String passwordHash;
//}

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false, columnDefinition = "TEXT")
    private String passwordHash;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

}
