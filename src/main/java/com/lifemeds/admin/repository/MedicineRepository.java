package com.lifemeds.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifemeds.admin.model.Category;
import com.lifemeds.admin.model.Medicine;
import com.lifemeds.admin.model.Seller;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
	public List <Medicine> findByisEnabled(boolean enabled);
	
	public List <Medicine> findByCategoryAndIsEnabled(Category cat, boolean enabled);
	
	public List <Medicine> findBySellerAndIsEnabled(Seller sell, boolean enabled);
	
	public List <Medicine> findByMedicineNameContainsAndIsEnabled(String tag, boolean enabled);
	
	//@Query(value = "select * from medicine where medicine_name ~* :term", nativeQuery = true)
	//public List<Object[]> findMovies(@Param("term") String term);

}
