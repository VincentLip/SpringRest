package com.example.tpvendredi_07_juillet.dto;


import com.example.tpvendredi_07_juillet.entity.Comment;
import com.example.tpvendredi_07_juillet.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    private Integer id;

    @NotEmpty(message = "title should not be null or empty")
    @Size(min = 4 ,max = 15 , message = "Votre titre doit avoir entre 4 et 15 caracteres")
    private String title;

    @NotEmpty(message = "text should not be null or empty")
    @Size(max = 250,message = "Caracteres maximum de 250")
    private String text;


    private UserDto userDto;

    private Set<CommentDto> commentDtos;
}
