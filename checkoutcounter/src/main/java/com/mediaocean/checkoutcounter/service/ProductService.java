package com.mediaocean.checkoutcounter.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediaocean.checkoutcounter.bean.ItemizedBill;
import com.mediaocean.checkoutcounter.bean.Product;
import com.mediaocean.checkoutcounter.repository.ProductRepository;
import com.mediaocean.checkoutcounter.type.ProductCategory;
import com.mediaocean.checkoutcounter.util.AppConstants;

/**
 * Service class to define all Product Operations
 * 
 * @author Gourang Jyani
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public boolean addProduct(Product product) {
		return repository.addProduct(product);
	}

	public List<Product> getAllProducts() {
		return repository.getAllProducts();

	}

	public Product getProduct(Long productId) {
		return repository.getProduct(productId);

	}

	public boolean removeProduct(Long productId) {
		return repository.removeProduct(productId);

	}

	public boolean validateProductPacket(Product product) {
		if (product == null || product.getProductId() == null || product.getProductId().longValue() <= 0 || product.getQuantity().intValue() <= 0 || product.getUnitPrice().compareTo(new BigDecimal(0.00D)) <= 0)
			return false;
		else
			return true;

	}

	public boolean checkDuplicate(Product product) {

		List<Product> productList = repository.getAllProducts();
		if(productList.isEmpty()) {
			return false;
		}
		return productList.stream().filter(p -> p.getProductId() == product.getProductId()).findFirst().isPresent();
	}

	public ItemizedBill generateProductBill() {
		List<Product> productList = repository.getAllProducts();
		ItemizedBill bill = new ItemizedBill();

		productList.forEach(item -> {

			BigDecimal billAmountExclusiveOfTax = bill.getBillAmountExclusiveOfTax() != null ? bill.getBillAmountExclusiveOfTax() : new BigDecimal(0.0D);
			BigDecimal taxAmount = bill.getTotalTaxAmount() != null ? bill.getTotalTaxAmount() : new BigDecimal(0.0D);
			BigDecimal netBillAmount = bill.getNetBillAmount() != null ? bill.getNetBillAmount() : new BigDecimal(0.0D);
			System.out.println("billAmountExclusiveOfTax: " + billAmountExclusiveOfTax);
			System.out.println("taxAmount: " + taxAmount);
			System.out.println("netBillAmount: " + netBillAmount);
			System.out.println("item.getNetItemPrice(): " + item.getNetItemPrice());

			if (item.getCategogy().equals(ProductCategory.CATEGORY_A)) {
				System.out.println("Item Tax(): " + item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_A).divide(new BigDecimal(100.00D)));
				taxAmount = taxAmount.add(item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_A).divide(new BigDecimal(100.00D)));
			} else if (item.getCategogy().equals(ProductCategory.CATEGORY_B)) {
				System.out.println("Item Tax(): " + item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_B).divide(new BigDecimal(100.00D)));
				taxAmount = taxAmount.add(item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_B).divide(new BigDecimal(100.00D)));
			} else if (item.getCategogy().equals(ProductCategory.CATEGORY_C)) {
				System.out.println("Item Tax(): " + item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_C).divide(new BigDecimal(100.00D)));
				taxAmount = taxAmount.add(item.getNetItemPrice().multiply(AppConstants.LEVY_CATEGORY_C).divide(new BigDecimal(100.00D)));
			}

			billAmountExclusiveOfTax = billAmountExclusiveOfTax.add(item.getNetItemPrice());
			netBillAmount = billAmountExclusiveOfTax.add(taxAmount);
			bill.setBillAmountExclusiveOfTax(billAmountExclusiveOfTax);
			bill.setTotalTaxAmount(taxAmount);
			bill.setNetBillAmount(netBillAmount);
			System.out.println("billAmountExclusiveOfTax: " + billAmountExclusiveOfTax);
			System.out.println("taxAmount: " + taxAmount);
			System.out.println("netBillAmount: " + netBillAmount);
			System.out.println("-------------------");

		});
		bill.setProductList(productList);
		return bill;
	}

}
