package com.megha.blogapplicationbackend.repository;

import com.megha.blogapplicationbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
