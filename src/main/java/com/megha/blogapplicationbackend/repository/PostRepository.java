package com.megha.blogapplicationbackend.repository;

import com.megha.blogapplicationbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
