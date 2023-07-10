package com.example.tpvendredi_07_juillet.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    private String username;

    @Pattern(regexp = "(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]{2})(?=.*[@#$%^&+!=])(?=.{8,8}).*$")
    private String password;

    private Set<PostDto> postDtos ;


}
