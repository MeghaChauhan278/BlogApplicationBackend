package com.megha.blogapplicationbackend.service.impl;

import com.megha.blogapplicationbackend.dto.CommentDTO;
import com.megha.blogapplicationbackend.entity.Comment;
import com.megha.blogapplicationbackend.entity.Post;
import com.megha.blogapplicationbackend.repository.CommentRepository;
import com.megha.blogapplicationbackend.repository.PostRepository;
import com.megha.blogapplicationbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        Post post = findPostById(commentDTO.getPostId());
        Comment comment = toEntity(commentDTO, post);
        Comment savedComment = commentRepository.save(comment);
        return toDTO(savedComment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        Comment comment = findCommentById(id);
        return toDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = findCommentById(id);
        Post post = findPostById(commentDTO.getPostId());

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);

        Comment updatedComment = commentRepository.save(comment);
        return toDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = findCommentById(id);
        commentRepository.delete(comment);
    }

    private Comment findCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Comment not found with id: " + id
                ));
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Post not found with id: " + id
                ));
    }

    private Comment toEntity(CommentDTO commentDTO, Post post) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());
        comment.setPost(post);
        return comment;
    }

    private CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setContent(comment.getContent());
        commentDTO.setPostId(comment.getPost().getId());
        return commentDTO;
    }
}
