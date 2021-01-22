package com.lifemeds.admin.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_purchase")
	private int idPurchase;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	@Column(name="amount_payed")
	private double amountPayed;
	
	@Column(name="delivery_address")
	private String deliveryAddress;
	
	@Column(name="purchase_date")
	private Date purchaseDate = new Date();
	
	@OneToMany(mappedBy = "purchase")	
	private List<ProductPurchase> productPurchase;

	public int getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getAmountPayed() {
		return amountPayed;
	}

	public void setAmountPayed(double amountPayed) {
		this.amountPayed = amountPayed;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}	

	public void setProductPurchase(List<ProductPurchase> productPurchase) {
		this.productPurchase = productPurchase;
	}	

	@Override
	public String toString() {
		return "Purchase [idPurchase=" + idPurchase + ", user=" + user + ", amountPayed=" + amountPayed
				+ ", deliveryAddress=" + deliveryAddress + ", purchaseDate=" + purchaseDate + "]";
	}

	
}
