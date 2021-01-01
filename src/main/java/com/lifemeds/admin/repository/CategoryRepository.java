package com.lifemeds.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifemeds.admin.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public List <Category> findBycategoryName(String category);
}
