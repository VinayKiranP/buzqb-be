package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Stock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock, Long> {

  @Query(value = "SELECT * FROM stock WHERE business_id = :businessId", nativeQuery = true)
  List<Stock> getAllStockByBusiness(@Param("businessId") Long businessId);
}
