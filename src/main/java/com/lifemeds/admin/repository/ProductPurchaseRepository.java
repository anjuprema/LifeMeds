package com.lifemeds.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.ProductPurchase;

public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Integer> {

}
