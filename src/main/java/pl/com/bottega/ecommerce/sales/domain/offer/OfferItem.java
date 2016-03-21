/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

	private ProductSnapshot productSnapshot;
	private Discount discount;
	private Money totalCost;
	
	private int quantity;

	public OfferItem(ProductSnapshot productSnapshot, int quantity) {
		this(productSnapshot, quantity, null);
	}

	public OfferItem(ProductSnapshot productSnapshot, int quantity,	Discount discount) {
		this.productSnapshot = productSnapshot;
		this.quantity = quantity;
		this.discount = discount;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount.getDiscount() != null)
			discountValue = discountValue.subtract(discount.getDiscount());

		this.totalCost.setValue(this.productSnapshot.getProductPrice().getValue()
				.multiply(new BigDecimal(quantity)).subtract(discountValue));
	}

	public ProductSnapshot getProductSnapshot() {
		return productSnapshot;
	}

	public Money getTotalCost() {
		return totalCost;
	}

	public Discount getDiscount() {
		return discount;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount.getDiscount() == null) ? 0 : discount.getDiscount().hashCode());
		result = prime * result + ((productSnapshot == null) ? 0 : productSnapshot.hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((totalCost.getValue() == null) ? 0 : totalCost.getValue().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferItem other = (OfferItem) obj;
		if (discount.getDiscount() == null) {
			if (other.discount.getDiscount() != null)
				return false;
		} else if (!discount.getDiscount().equals(other.discount.getDiscount()))
			return false;
		if (productSnapshot == null) {
			if (other.productSnapshot != null)
				return false;
		} else if (!productSnapshot.equals(other.productSnapshot))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalCost.getValue() == null) {
			if (other.totalCost.getValue() != null)
				return false;
		} else if (!totalCost.getValue().equals(other.totalCost.getValue()))
			return false;
		return true;
	}

	/**
	 * 
	 * @param item
	 * @param delta
	 *            acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (productSnapshot == null) {
			if (other.productSnapshot != null)
				return false;
		} else if (!productSnapshot.equals(other.productSnapshot))
			return false;
		if (quantity != other.quantity)
			return false;

		BigDecimal max, min;
		if (totalCost.getValue().compareTo(other.totalCost.getValue()) > 0) {
			max = totalCost.getValue();
			min = other.totalCost.getValue();
		} else {
			max = other.totalCost.getValue();
			min = totalCost.getValue();
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
