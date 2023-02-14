package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.models.CategoryDTO;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api")
public class RestCategory {
	
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	//GET Recupera la categoría correspondiente a ese id
	@GetMapping("/categories/{categoryId}")
	public ResponseEntity<?> getCategoryResp(@PathVariable int categoryId)
	{
		CategoryDTO category=categoryService.findCategoryByIdModel(categoryId);
		if(category==null)
			return ResponseEntity.notFound().build();
		else
		return ResponseEntity.ok(category);
	}

	//POST Crea una nueva categoría
	@PostMapping("/categories")
	public ResponseEntity<?> insertCategoryNew (@RequestBody CategoryDTO category)
	{
		categoryService.addCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
		
	}
	
	//PUT Actualiza una categoría
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<?> updateCategoryNew(@RequestBody CategoryDTO category,@PathVariable int categoryId)
	{
		Category c=categoryService.findCategoryById(categoryId);
		c.setName(category.getName());
		c.setDescription(category.getDescription());
		return ResponseEntity.ok(categoryService.addCategory(categoryService.transform(c)));
	}
	
	//DELETE Elimina una categoría y todos sus productos (categoría correspondiente a ese id)
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<?> deleteCategoryNew(@PathVariable int categoryId)
	{
		boolean exists = categoryService.removeCategory(categoryId);
		if(exists)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}
}
