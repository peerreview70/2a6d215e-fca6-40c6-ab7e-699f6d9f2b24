package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

public class ProductSnapshot {
	private String productId;
	private Money productPrice;
	private String productName;
	private Date productSnapshotDate;
	private String productType;
	
	public ProductSnapshot(String productId, Money productPrice,
			String productName, Date productSnapshotDate, String productType) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.productName = productName;
		this.productSnapshotDate = productSnapshotDate;
		this.productType = productType;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public Money getProductPrice() {
		return productPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Date getProductSnapshotDate() {
		return productSnapshotDate;
	}
	
	public String getProductType() {
		return productType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productPrice.getValue() == null) ? 0 : productPrice.getValue().hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
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
		ProductSnapshot other = (ProductSnapshot) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (productPrice.getValue() == null) {
			if (other.productPrice.getValue() != null)
				return false;
		} else if (!productPrice.getValue().equals(other.productPrice.getValue()))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType != other.productType)
			return false;
		return true;
	}
}
