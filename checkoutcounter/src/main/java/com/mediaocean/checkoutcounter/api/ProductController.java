package com.mediaocean.checkoutcounter.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediaocean.checkoutcounter.bean.ItemizedBill;
import com.mediaocean.checkoutcounter.bean.Product;
import com.mediaocean.checkoutcounter.service.ProductService;
/**
 * This class provides API's for all Operation required for Product Counter. This will serve as API end point.
 * 
 * @author Gourang Jyani
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {
	@Autowired
	private ProductService service;

	/**
	 * API for Post Operation, Add new Product to Cart
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if (!service.validateProductPacket(product)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// Check if product already exists in the cart
		if (service.checkDuplicate(product)) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		product.setNetItemPrice(product.getUnitPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
		service.addProduct(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	/**
	 * API for Get Operation, Returns all products in Cart
	 * 
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> viewProducts() {
		List<Product> productList = service.getAllProducts();
		if (productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	/**
	 * API for Get Operation, Returns product with specified productId in Cart
	 * 
	 * @param productId
	 * @return
	 */
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> viewProduct(@PathVariable(value = "id") Long productId) {
		Product product = service.getProduct(productId);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/**
	 * API for Delete Operation, Deletes product with specified productId from Cart
	 * 
	 * @param productId
	 * @return
	 */
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> removeProduct(@PathVariable(value = "id") Long productId) {
		boolean operationFlag = service.removeProduct(productId);
		if (!operationFlag) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/**
	 * API for GET Operation, Returns Itemized Bill for all products from Cart
	 * 
	 * @return
	 */
	@GetMapping(path = "/bill", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemizedBill> generateProductBill() {
		ItemizedBill bill = service.generateProductBill();
		if (bill.getProductList().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<ItemizedBill>(bill, HttpStatus.OK);

	}
}
