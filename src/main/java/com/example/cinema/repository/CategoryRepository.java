package com.example.cinema.repository;

import com.example.cinema.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);
}
