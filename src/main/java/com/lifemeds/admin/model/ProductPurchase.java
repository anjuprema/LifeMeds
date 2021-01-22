package com.lifemeds.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_purchase")
public class ProductPurchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_product_purchase")
	private int idProductPurchase;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_purchase")
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(name="id_medicine")
	private Medicine medicine;
	
	@Column(name="item_count")
	private int itemCount;
	
	@Column(name="product_price")
	private double productPrice;

	public int getIdProductPurchase() {
		return idProductPurchase;
	}

	public void setIdProductPurchase(int idProductPurchase) {
		this.idProductPurchase = idProductPurchase;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductPurchase [idProductPurchase=" + idProductPurchase + ", purchase=" + purchase + ", medicine="
				+ medicine + ", itemCount=" + itemCount + ", productPrice=" + productPrice + "]";
	}
}
