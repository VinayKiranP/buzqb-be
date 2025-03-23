package com.buz.buzqb.service.impl;

import com.buz.buzqb.entity.Role;
import com.buz.buzqb.entity.Stock;
import com.buz.buzqb.repository.StockRepo;
import com.buz.buzqb.service.StockService;
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
public class StockServiceImpl implements StockService {

  private final StockRepo stockRepo;

  @Autowired
  public StockServiceImpl(StockRepo stockRepo) {
    this.stockRepo = stockRepo;
  }

  @Override
  public List<Stock> getAllStock() {
    return stockRepo.findAll();
  }

  @Override
  @Cacheable(value = "stock")
  public Optional<Stock> getStockById(Long id) {
    var data = stockRepo.findById(id);
    var entity = data.map(stock -> Hibernate.unproxy(stock, Stock.class)).orElse(null);
    return Optional.ofNullable(entity);
  }

  @Override
  @CachePut(value = "stock", key = "#stock.id")
  public Stock saveStock(Stock stock) {
    return stockRepo.save(stock);
  }

  @Override
  @CacheEvict(value = "stock", key = "#stock.id")
  public Stock updateStock(Stock stock) {
    return stockRepo.save(stock);
  }

  @Override
  public List<Stock> getAllStockByBusiness(Long businessId) {
    return stockRepo.getAllStockByBusiness(businessId);
  }
}
