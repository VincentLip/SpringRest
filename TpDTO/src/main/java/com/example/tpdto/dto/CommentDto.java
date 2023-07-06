package com.example.tpdto.dto;

import com.example.tpdto.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer id;

    private String body;

    private String email;

    private String name;

    private Date date;
    private Post post;
}
