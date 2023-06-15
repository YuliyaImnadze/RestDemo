
package com.example.restdemo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {

    private Integer id;

    @Size(min = 2, max = 20)
    private  String name;

    @Min(value = 1)
    @Max(value = 100)
    private  Integer age;

    @Email
    @Size(max = 50)
    private  String email;

    @Size(min = 2, max = 20)
    @NotBlank
    private  String nickname;

}