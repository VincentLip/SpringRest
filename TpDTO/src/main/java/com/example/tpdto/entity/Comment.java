package com.example.tpdto.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id", nullable = false)
    private Integer id;

    @Column(name = "body")
    private String body;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "posts_id", nullable = false)
    private Post posts;

}