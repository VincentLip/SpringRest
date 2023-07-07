package com.example.tpvendredi_07_juillet.dto;

import com.example.tpvendredi_07_juillet.entity.Dislike;
import com.example.tpvendredi_07_juillet.entity.Like;
import com.example.tpvendredi_07_juillet.entity.Post;
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
public class CommentDto {

    private Integer id;
    @NotEmpty(message = "message should not be null or empty")
    @Size(max = 500,message = "Caracteres maximum de 500")
    private String message;


    private PostDto postDto;


    private Set<LikeDto> likeDtos ;


    private Set<DislikeDto> dislikeDtos ;
}
