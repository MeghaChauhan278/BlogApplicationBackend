package com.megha.blogapplicationbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category title cannot be blank")
    private String title;

    @NotBlank(message = "Category description cannot be blank")
    private String description;
}
