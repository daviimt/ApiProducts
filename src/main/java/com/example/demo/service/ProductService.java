package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.models.ProductDTO;

public interface ProductService {
	
	public abstract ProductDTO addProduct(ProductDTO productDTO);
	public abstract List<ProductDTO> listAllProducts();
	public abstract Product findProductById(int id);
	public abstract ProductDTO findProductByIdModel(int id);
	public abstract boolean removeProduct(int id);
	public abstract Product transform(ProductDTO productDTO);
	public abstract ProductDTO transform(Product product);
	
}
