package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.models.ProductDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.serviceImpl.UserService;

@RestController
@RequestMapping("/api")
public class RestProduct {
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/user/products")
	public ResponseEntity<?> getProducts() {
		boolean exist = productService.listAllProducts()!=null;
		if(exist) {
			List<ProductDTO> product=productService.listAllProducts();
			return ResponseEntity.ok(product);
		}
		else
			return ResponseEntity.noContent().build();

	}
	
	// GET Recupera el producto correspondiente a ese id
	@GetMapping("/admin/products/{productId}")
	public ResponseEntity<?> getProductResp(@PathVariable int productId) {
		boolean exist = productService.findProductById(productId)!=null;
		if(exist) {
			ProductDTO product=productService.findProductByIdModel(productId);
			return ResponseEntity.ok(product);
		}
		else
			return ResponseEntity.noContent().build();

	}

	// PUT Actualiza un producto
	@PutMapping("/admin/products/{productId}")
	public ResponseEntity<?> updateProductNew(@RequestBody ProductDTO product,@PathVariable int productId) {
		boolean exist = productService.findProductById(productId)!=null;
		if(exist) {
			Product c=productService.findProductById(productId);
			c.setName(product.getName());
			c.setDescription(product.getDescription());
			c.setPrice(product.getPrice());
			return ResponseEntity.ok(productService.addProduct(productService.transform(c)));
			}
		else {
			return ResponseEntity.noContent().build();
		}
		
	}

	// DELETE Elimina una categoría y todos sus productos (categoría correspondiente
	// a ese id)
	@DeleteMapping("/admin/products/{productId}")
	public ResponseEntity<?> deleteProductNew(@PathVariable int productId) {
		boolean exists = productService.removeProduct(productId);
		if (exists)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.noContent().build();
	}

	// GET Recupera todos los productos de una determinada categoría
	@GetMapping("/user/categories/{categoryId}/products")
	public ResponseEntity<?> getProductCategory(@PathVariable int categoryId) {
		boolean exist = categoryService.findCategoryById(categoryId)==null;
		if(exist) {
			return ResponseEntity.noContent().build();
		}
		else {
			List<ProductDTO> list = productService.findProductsByCategoryId(categoryId);
			return ResponseEntity.ok(list);
		}
	}

	// POST Crea un nuevo producto para una categoría
	@PostMapping("/admin/categories/{categoryId}/product")
	public ResponseEntity<?> insertProductCategory(@RequestBody ProductDTO product,@PathVariable int categoryId) {
		product.setIdCategory(categoryId);
		productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);

	}
	
	// DELETE Elimina todos los productos de una determinada categoría
	@DeleteMapping("/admin/categories/{categoryId}/products")
	public ResponseEntity<?> deleteProductsCategory(@PathVariable int categoryId) {
		boolean exists = productService.deleteProductsByCategoryId(categoryId);
		if (exists)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/user/addFav/{id}")
	private ResponseEntity<?> addFav(@PathVariable int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.addFav(id,username);
		return ResponseEntity.ok().build();
	}
	
}
