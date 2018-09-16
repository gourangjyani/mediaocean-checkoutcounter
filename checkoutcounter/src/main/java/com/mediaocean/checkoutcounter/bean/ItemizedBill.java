package com.mediaocean.checkoutcounter.bean;

import java.math.BigDecimal;
import java.util.List;

public class ItemizedBill {

	private List<Product> productList = null;
	private BigDecimal billAmountExclusiveOfTax;
	private BigDecimal totalTaxAmount;
	private BigDecimal netBillAmount;
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public BigDecimal getBillAmountExclusiveOfTax() {
		return billAmountExclusiveOfTax;
	}
	public void setBillAmountExclusiveOfTax(BigDecimal billAmountExclusiveOfTax) {
		this.billAmountExclusiveOfTax = billAmountExclusiveOfTax;
	}
	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	public BigDecimal getNetBillAmount() {
		return netBillAmount;
	}
	public void setNetBillAmount(BigDecimal netBillAmount) {
		this.netBillAmount = netBillAmount;
	}
	@Override
	public String toString() {
		return "ItemizedBill [productList=" + productList + ", billAmountExclusiveOfTax=" + billAmountExclusiveOfTax + ", totalTaxAmount=" + totalTaxAmount + ", netBillAmount=" + netBillAmount + "]";
	}

}
