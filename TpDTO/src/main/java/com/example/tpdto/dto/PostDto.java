package com.example.tpdto.dto;

import com.example.tpdto.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer id;

    private String content;

    private String description;

    private String title;
    private Date date;
    private Set<Comment> comments = new LinkedHashSet<>();
}
