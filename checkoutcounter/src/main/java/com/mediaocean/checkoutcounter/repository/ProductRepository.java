package com.mediaocean.checkoutcounter.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mediaocean.checkoutcounter.bean.Product;

/**
 * This class provides implementation of a Mock Repository. This is responsible for all Database Operation
 * @author Gourang Jyani
 *
 */
//@Repository
@Component
public class ProductRepository {

	private static List<Product> productList = null;

	public boolean addProduct(Product product) {
		if (productList == null) {
			productList = new ArrayList<Product>();
		}
		System.out.println("Product List" + productList);
		return productList.add(product);
	}

	public List<Product> getAllProducts() {
		if (productList == null) {
			return Collections.emptyList();
		}
		return productList;
	}

	public Product getProduct(Long productId) {
		if (productList == null) {
			return null;
		}
		return productList.stream().filter(p -> p.getProductId().longValue() == productId.longValue()).findFirst().get();
	}

	public boolean removeProduct(Long productId) {
		Iterator<Product> iter = productList.iterator();
		boolean removeFlag = false;
		while (iter.hasNext()) {
			Product p = iter.next();
			if (p.getProductId().longValue() == productId.longValue()) {
				iter.remove();
				removeFlag = true;
			}
		}
		return removeFlag;
	}
}
