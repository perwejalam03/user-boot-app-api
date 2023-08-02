package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao pdao;

	public ResponseStructure<Product> saveProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setMessage("Product saved successfully");
		structure.setBody(pdao.saveProduct(product));
		structure.setCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseStructure<Product> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setMessage("Product updated successfully");
		structure.setBody(pdao.updateProduct(product));
		structure.setCode(HttpStatus.ACCEPTED.value());
		return structure;
	}

	public ResponseStructure<Product> findProduct(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = pdao.findProduct(id);
		if (recProduct.isPresent()) {
			structure.setBody(recProduct.get());
			structure.setMessage("Product Found");
			structure.setCode(HttpStatus.OK.value());
			return structure;
		}
		structure.setBody(null);
		structure.setMessage("Product Not Found");
		structure.setCode(HttpStatus.NOT_FOUND.value());
		return structure;
	}

	public ResponseStructure<List<Product>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setBody(pdao.findAll());
		structure.setMessage("All Product are displayed ");
		structure.setCode(HttpStatus.OK.value());
		return structure;
	}

	public ResponseStructure<String> deleteProduct(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Product> recProduct = pdao.findProduct(id);
		if (recProduct.isPresent()) {
			pdao.deleteProduct(id);
			structure.setBody("Product deleted ");
			structure.setMessage("Product Found");
			structure.setCode(HttpStatus.OK.value());
			return structure;
		}
		structure.setBody("Product Not deleted ");
		structure.setMessage("Product Not Found");
		structure.setCode(HttpStatus.NOT_FOUND.value());
		return structure;
	}
}
