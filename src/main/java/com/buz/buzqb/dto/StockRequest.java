package com.buz.buzqb.dto;

import com.buz.buzqb.entity.Stock;
import com.buz.buzqb.entity.StockMovement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StockRequest {

  @NotNull
  @Size(max = 50, message = "code can't exceed 50 characters")
  private String code;
  private String modelNumber;
  private String description;
  private Long available;
  private Long forReserve;
  private Double mrp;
  private Double purchasedPrice;
  private Double sellingPrice;
  private int status;
  @NotNull
  private Long businessId;

  public Stock requestToStock(StockRequest stockRequest) {
    Stock stock = new Stock();
    stock.setCode(stockRequest.getCode());
    stock.setModelNumber(stockRequest.getModelNumber());
    stock.setDescription(stockRequest.getDescription());
    stock.setAvailable(stockRequest.getAvailable());
    stock.setForReserve(stockRequest.getForReserve());
    stock.setMrp(stockRequest.getMrp());
    stock.setPurchasedPrice(stockRequest.getPurchasedPrice());
    stock.setSellingPrice(stockRequest.getSellingPrice());
    stock.setStatus(stockRequest.getStatus());
    stock.setBusinessId(stockRequest.getBusinessId());
    return stock;
  }

  public StockMovement requestToStockMovement(StockRequest stockRequest) {
    StockMovement stockMovement = new StockMovement();
    stockMovement.setCode(stockRequest.getCode());
    stockMovement.setModelNumber(stockRequest.getModelNumber());
    stockMovement.setDescription(stockRequest.getDescription());
    stockMovement.setAvailable(stockRequest.getAvailable());
    stockMovement.setForReserve(stockRequest.getForReserve());
    stockMovement.setMrp(stockRequest.getMrp());
    stockMovement.setPurchasedPrice(stockRequest.getPurchasedPrice());
    stockMovement.setSellingPrice(stockRequest.getSellingPrice());
    stockMovement.setStatus(0);
    stockMovement.setBusinessId(stockRequest.getBusinessId());
    return stockMovement;
  }
}