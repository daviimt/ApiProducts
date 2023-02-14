package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api")
public class RestProduct {
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	
	//GET Recupera el producto correspondiente a ese id
	@GetMapping("/products/{productId}")
	public ResponseEntity<?> getProductResp(@PathVariable int productId)
	{
		ProductDTO product=productService.findProductByIdModel(productId);
		if(product==null)
			return ResponseEntity.notFound().build();
		else
		return ResponseEntity.ok(product);
	}
	
	//PUT Actualiza un producto
	@PutMapping("/products/{productId}")
	public ResponseEntity<?> updateProductNew(@RequestBody ProductDTO product)
	{
		return ResponseEntity.ok(productService.addProduct(product));
	}
	
	//DELETE Elimina una categoría y todos sus productos (categoría correspondiente a ese id)
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductNew(@PathVariable int productId)
	{
		boolean exists = productService.removeProduct(productId);
		if(exists)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.notFound().build();
	}
}
