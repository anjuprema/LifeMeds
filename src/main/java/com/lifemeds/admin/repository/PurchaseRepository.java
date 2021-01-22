package com.lifemeds.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.Purchase;
import com.lifemeds.admin.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	public List<Purchase> findByUserOrderByPurchaseDateDesc(User user);
}
