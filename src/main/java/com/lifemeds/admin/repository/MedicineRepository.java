package com.lifemeds.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

}
