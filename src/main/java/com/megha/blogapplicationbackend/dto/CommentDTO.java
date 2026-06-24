package com.megha.blogapplicationbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Comment name cannot be blank")
    private String name;

    @NotBlank(message = "Comment email cannot be blank")
    private String email;

    @NotBlank(message = "Comment content cannot be blank")
    private String content;

    private Long postId;
}
