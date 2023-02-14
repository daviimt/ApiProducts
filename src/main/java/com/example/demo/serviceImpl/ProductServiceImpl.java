package com.example.demo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.models.ProductDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productRepository")
	private ProductRepository productRepository;

	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		productRepository.save(transform(productDTO));
		return null;
	}

	@Override
	public List<ProductDTO> listAllProducts() {
		return productRepository.findAll().stream().map(c -> transform(c)).collect(Collectors.toList());
	}

	@Override
	public Product findProductById(int id) {
		return productRepository.findById(id);
	}

	@Override
	public ProductDTO findProductByIdModel(int id) {

		return transform(productRepository.findById(id));
	}

	@Override
	public boolean removeProduct(int id) {
		if (productRepository.findById(id) != null) {
			productRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	@Override
	public Product transform(ProductDTO productDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(productDTO, Product.class);
	}

	@Override
	public ProductDTO transform(Product product) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(product, ProductDTO.class);
	}

	@Override
	public List<ProductDTO> findProductsByCategoryId(int categoryId) {

		List<ProductDTO> listProducts = productRepository.findByIdCategory(categoryId).stream().map(c -> transform(c))
				.collect(Collectors.toList());

		return listProducts;
	}

	@Override
	public boolean deleteProductsByCategoryId(int categoryId) {
		List<ProductDTO> listProducts = findProductsByCategoryId(categoryId);

		listProducts.stream().map(c -> removeProduct(c.getId()));
		
		
		return true;
	}

}
