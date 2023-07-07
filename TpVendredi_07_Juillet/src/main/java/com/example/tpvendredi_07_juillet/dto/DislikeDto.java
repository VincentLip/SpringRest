package com.example.tpvendredi_07_juillet.dto;


import com.example.tpvendredi_07_juillet.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DislikeDto {

    private Integer id;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date date;

    private CommentDto commentDto;
}
