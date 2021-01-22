package com.lifemeds.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.ProductPurchase;
import com.lifemeds.admin.model.Purchase;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Integer> {
	public List<ProductPurchase> findByPurchase(Purchase purchase);
}
