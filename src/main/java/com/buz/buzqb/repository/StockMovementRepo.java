package com.buz.buzqb.repository;

import com.buz.buzqb.entity.Stock;
import com.buz.buzqb.entity.StockMovement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMovementRepo extends JpaRepository<StockMovement, Long> {

  @Query(value = "SELECT * FROM stock_movement WHERE business_id = :businessId AND status = :status", nativeQuery = true)
  List<StockMovement> getAllStockMovementByBusinessNStatus(@Param("businessId") Long businessId, @Param("status") int status);
}
