package com.megha.blogapplicationbackend.service;

import com.megha.blogapplicationbackend.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO);

    List<CommentDTO> getAllComments();

    CommentDTO getCommentById(Long id);

    CommentDTO updateComment(Long id, CommentDTO commentDTO);

    void deleteComment(Long id);
}
