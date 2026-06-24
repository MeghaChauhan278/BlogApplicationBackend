package com.megha.blogapplicationbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank(message = "Post title cannot be blank")
    private String title;

    @NotBlank(message = "Post content cannot be blank")
    private String content;

    private Long categoryId;
}
