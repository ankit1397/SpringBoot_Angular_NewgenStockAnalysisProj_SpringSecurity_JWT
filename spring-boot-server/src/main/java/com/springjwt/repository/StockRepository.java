package com.springjwt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springjwt.models.StockData;

public interface StockRepository extends JpaRepository<StockData, Long> {
	
	//@Query(value = "SELECT * FROM stock_data WHERE DATE(date) >= ?1 and DATE(date) <= ?2", nativeQuery = true)
	//public List<StockData> findAllByDateGreaterThanEqualAndDateLessThanEqual(@Param("startDate") Date startDate,
			//@Param("endDate") Date endDate);
	
	@Query(value = "SELECT * FROM stock_data WHERE DATE(date) BETWEEN ?1 and ?2", nativeQuery = true)
	public List<StockData> findAllByDateBetween(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
