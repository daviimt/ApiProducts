package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Category;
import com.example.demo.models.CategoryDTO;

public interface CategoryService {

	public abstract CategoryDTO addCategory(CategoryDTO categoryDTO);
	public abstract List<CategoryDTO> listAllCategories();
	public abstract Category findCategoryById(int id);
	public abstract CategoryDTO findCategoryByIdModel(int id);
	public abstract boolean removeCategory(int id);
	public abstract Category transform(CategoryDTO categoryDTO);
	public abstract CategoryDTO transform(Category category);
}
