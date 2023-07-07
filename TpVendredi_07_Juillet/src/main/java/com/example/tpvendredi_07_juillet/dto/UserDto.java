package com.example.tpvendredi_07_juillet.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotEmpty(message = "userName should not be null or empty")
    private String userName;

    private Set<PostDto> postDtos ;
}
