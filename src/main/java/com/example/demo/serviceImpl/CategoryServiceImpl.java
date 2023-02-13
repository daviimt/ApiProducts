package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.models.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	@Qualifier("categoryRepository")
	private CategoryRepository categoryRepository;
	
	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		categoryRepository.save(transform(categoryDTO));
		return null;
	}

	@Override
	public List<CategoryDTO> listAllCategories() {
		return categoryRepository.findAll().stream().
				map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public CategoryDTO findCategoryByIdModel(int id) {
		return transform(categoryRepository.findById(id));
	}

	@Override
	public boolean removeCategory(int id) {
		if(categoryRepository.findById(id)!=null) {
			categoryRepository.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public Category transform(CategoryDTO categoryDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoryDTO, Category.class);
	}

	@Override
	public CategoryDTO transform(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(category, CategoryDTO.class);
	}

}
