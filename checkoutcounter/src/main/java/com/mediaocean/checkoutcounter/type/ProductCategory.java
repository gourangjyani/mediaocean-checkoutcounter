package com.mediaocean.checkoutcounter.type;

/**
 * This class provides defines Different Product Categories available.
 * 
 * @author Gourang Jyani
 */
public enum ProductCategory {
	CATEGORY_A, CATEGORY_B, CATEGORY_C;

	public static ProductCategory fromValue(String value) {
		if (value.equals("A")) {
			return CATEGORY_A;
		} else if (value.equals("B")) {
			return CATEGORY_B;
		} else if (value.equals("C")) {
			return CATEGORY_C;
		} else
			return null;
	}
	public static String toString(ProductCategory category) {
		if (category.equals(CATEGORY_A)) {
			return "A";
		} else if (category.equals(CATEGORY_B)) {
			return "B";
		} else if (category.equals(CATEGORY_C)) {
			return "C";
		} else
			return null;
	}
}
