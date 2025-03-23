package com.buz.buzqb.service;

import com.buz.buzqb.entity.Stock;
import java.util.List;
import java.util.Optional;

public interface StockService {

  List<Stock> getAllStock();

  Optional<Stock> getStockById(Long id);

  Stock saveStock(Stock stock);

  Stock updateStock(Stock stock);

  List<Stock> getAllStockByBusiness(Long businessId);
}
