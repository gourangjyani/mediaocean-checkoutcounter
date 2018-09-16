package com.mediaocean.checkoutcounter.bean;

import java.math.BigDecimal;

import com.mediaocean.checkoutcounter.type.ProductCategory;
/**
 * Domain Call for Products
 * @author Gourang Jyani
 *
 */
public class Product {

	private Long productId;
	private String productName;
	private Integer quantity;
	private BigDecimal unitPrice;
	private BigDecimal netItemPrice;
	private ProductCategory categogy;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getNetItemPrice() {
		return netItemPrice;
	}
	public void setNetItemPrice(BigDecimal netItemPrice) {
		this.netItemPrice = netItemPrice;
	}
	public ProductCategory getCategogy() {
		return categogy;
	}
	public void setCategogy(ProductCategory categogy) {
		this.categogy = categogy;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", netItemPrice=" + netItemPrice + ", categogy=" + categogy
				+ "]";
	}

}
