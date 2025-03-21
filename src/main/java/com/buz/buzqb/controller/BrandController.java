package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.BrandRequest;
import com.buz.buzqb.entity.Brand;
import com.buz.buzqb.service.BrandService;
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
@RequestMapping(Constants.V1_URI + Constants.BRAND_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class BrandController {

  private final BrandService brandService;
  public static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class.getName());

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  /**
   * Get Brand By Status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllBrand() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      long startTime = System.currentTimeMillis();
      response.setData(brandService.getAllBrand());
      long endTime = System.currentTimeMillis();
      LOGGER.info(Constants.TimeTakenToExecute+"getAllBrand: {}", endTime - startTime);
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getAllBrand error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get Brand By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBrandById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(brandService.getBrandById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getBrandById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add Brand
   * @param brandRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addBrand(@RequestBody BrandRequest brandRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Brand brand = brandRequest.requestToBrand(brandRequest);
      response.setData(brandService.saveBrand(brand));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"addBrand error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Brand
   * @param id
   * @param brandRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBrand(@PathVariable Long id,
      @RequestBody BrandRequest brandRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Brand> brand = brandService.getBrandById(id);
      if (brand.isPresent()) {
        Brand updatedBrand = brandRequest.requestToBrand(brandRequest);
        updatedBrand.setId(id);
        response.setData(brandService.updateBrand(updatedBrand));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(brand);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"updateBrand error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
