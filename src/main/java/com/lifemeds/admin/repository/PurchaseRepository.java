package com.lifemeds.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}
