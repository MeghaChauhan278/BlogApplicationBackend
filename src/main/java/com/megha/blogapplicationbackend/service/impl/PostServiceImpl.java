package com.megha.blogapplicationbackend.service.impl;

import com.megha.blogapplicationbackend.dto.PostDTO;
import com.megha.blogapplicationbackend.entity.Category;
import com.megha.blogapplicationbackend.entity.Post;
import com.megha.blogapplicationbackend.repository.CategoryRepository;
import com.megha.blogapplicationbackend.repository.PostRepository;
import com.megha.blogapplicationbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Category category = findCategoryById(postDTO.getCategoryId());
        Post post = toEntity(postDTO, category);
        Post savedPost = postRepository.save(post);
        return toDTO(savedPost);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = findPostById(id);
        return toDTO(post);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = findPostById(id);
        Category category = findCategoryById(postDTO.getCategoryId());

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return toDTO(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = findPostById(id);
        postRepository.delete(post);
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Post not found with id: " + id
                ));
    }

    private Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with id: " + id
                ));
    }

    private Post toEntity(PostDTO postDTO, Category category) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCategory(category);
        return post;
    }

    private PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setCategoryId(post.getCategory().getId());
        return postDTO;
    }
}
