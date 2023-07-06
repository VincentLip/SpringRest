package com.example.tpdto.entity;

import com.example.tpdto.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id", nullable = false)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "posts")
    private Set<Comment> comments = new LinkedHashSet<>();

}