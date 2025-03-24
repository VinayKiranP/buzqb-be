package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.StockMovement;
import com.buz.buzqb.repository.StockMovementRepo;
import com.buz.buzqb.service.StockMovementService;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class StockMovementServiceImpl implements StockMovementService {

  private final StockMovementRepo stockMovementRepo;

  @Autowired
  public StockMovementServiceImpl(StockMovementRepo stockMovementRepo) {
    this.stockMovementRepo = stockMovementRepo;
  }

  @Override
  public List<StockMovement> getAllStockMovement() {
    return stockMovementRepo.findAll();
  }

  @Override
  @Cacheable(value = "stockMovement")
  public Optional<StockMovement> getStockMovementById(Long id) {
    var data = stockMovementRepo.findById(id);
    var entity = data.map(stockMovement -> Hibernate.unproxy(stockMovement, StockMovement.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "stockMovement", key = "#stockMovement.id")
  public StockMovement saveStockMovement(StockMovement stockMovement) {
    return stockMovementRepo.save(stockMovement);
  }

  @Override
  @CacheEvict(value = "stockMovement", key = "#stockMovement.id")
  public StockMovement updateStockMovement(StockMovement stockMovement) {
    return stockMovementRepo.save(stockMovement);
  }

  @Override
  public List<StockMovement> getAllStockMovementByBusinessNStatus(Long businessId, int status) {
    return stockMovementRepo.getAllStockMovementByBusinessNStatus(businessId, status);
  }
}
