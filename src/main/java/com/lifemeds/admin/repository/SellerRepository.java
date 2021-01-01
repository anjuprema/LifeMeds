package com.lifemeds.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lifemeds.admin.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
	public List <Seller> findBysellerName(String seller);
}
