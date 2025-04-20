package com.buz.buzqb.service;

import com.buz.buzqb.entity.Stock;
import com.buz.buzqb.entity.StockMovement;
import java.util.List;
import java.util.Optional;

public interface StockMovementService {

  List<StockMovement> getAllStockMovement();

  Optional<StockMovement> getStockMovementById(Long id);

  StockMovement saveStockMovement(StockMovement stockMovement);

  StockMovement updateStockMovement(StockMovement stockMovement);

  List<StockMovement> getAllStockMovementByBusinessNStatus(Long businessId, int status);
}
