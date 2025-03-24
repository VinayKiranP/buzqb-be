package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.StockRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.entity.Stock;
import com.buz.buzqb.service.StockMovementService;
import com.buz.buzqb.service.StockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.V1_URI + Constants.STOCK_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class StockController extends BaseController {

  private final StockService stockService;
  private final StockMovementService stockMovementService;
  public static final Logger LOGGER = LoggerFactory.getLogger(StockController.class.getName());

  @Autowired
  public StockController(StockService stockService, StockMovementService stockMovementService) {
    this.stockService = stockService;
    this.stockMovementService = stockMovementService;
  }

  /**
   * Get Stock By Status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllStock() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Business business = authenticatedBusiness();
      long startTime = System.currentTimeMillis();
      if (business.getRoleId() > 2) {
        response.setData(stockMovementService.getAllStockMovementByBusinessNStatus(business.getId(), 0));
        long endTime = System.currentTimeMillis();
        LOGGER.info(Constants.TIME_TAKEN_TO_EXECUTE + "getAllStockMovementByBusinessNStatus: {}",
            endTime - startTime);
      } else {
        response.setData(stockService.getAllStockByBusiness(business.getId()));
        long endTime = System.currentTimeMillis();
        LOGGER.info(Constants.TIME_TAKEN_TO_EXECUTE + "getAllStockByBusiness: {}",
            endTime - startTime);
      }
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"getAllStock error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get Stock By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getStockById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(stockService.getStockById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"getStockById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add Stock
   * @param stockRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addStock(@RequestBody StockRequest stockRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Business business = authenticatedBusiness();
      if (business.getRoleId() > 2) {
        response.setData(stockMovementService.saveStockMovement(stockRequest.requestToStockMovement(stockRequest)));
      } else {
      Stock stock = stockRequest.requestToStock(stockRequest);
      response.setData(stockService.saveStock(stock));
      }
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"addStock error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Stock
   * @param id
   * @param stockRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateStock(@PathVariable Long id,
      @RequestBody StockRequest stockRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Stock> stock = stockService.getStockById(id);
      if (stock.isPresent()) {
        Stock updatedStock = stockRequest.requestToStock(stockRequest);
        updatedStock.setId(id);
        response.setData(stockService.updateStock(updatedStock));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(stock);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"updateStock error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
