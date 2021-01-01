package com.lifemeds.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="medicine")
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_medicine")
	private int idMedicine;
	@Column(name="medicine_name")
	private String medicineName;
	@OneToOne
	@JoinColumn(name="id_category")
	private Category category;
	@OneToOne
	@JoinColumn(name="id_seller")
	private Seller seller;
	@Column(name="medicine_price")
	private float medicinePrice;
	@Column(name="medicine_description")
	private String medicineDescription;
	@Column(name="picture_path")
	private String picturePath;
	@Column(name="is_enabled", nullable = false, columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isEnabled;
	public int getIdMedicine() {
		return idMedicine;
	}
	public void setIdMedicine(int idMedicine) {
		this.idMedicine = idMedicine;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public float getMedicinePrice() {
		return medicinePrice;
	}
	public void setMedicinePrice(float medicinePrice) {
		this.medicinePrice = medicinePrice;
	}
	public String getMedicineDescription() {
		return medicineDescription;
	}
	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Override
	public String toString() {
		return "Medicine [idMedicine=" + idMedicine + ", medicineName=" + medicineName + ", category=" + category
				+ ", seller=" + seller + ", medicinePrice=" + medicinePrice + ", medicineDescription="
				+ medicineDescription + ", picturePath=" + picturePath + ", isEnabled=" + isEnabled + "]";
	}
	
}
