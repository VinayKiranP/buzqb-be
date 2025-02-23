package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.service.BusinessService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(Constants.V1_URI + Constants.BUSINESS_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class BusinessController {

  private final BusinessService businessService;
  public static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class.getName());

  /**
   * Constructor
   * @param businessService
   */
  @Autowired
  private BusinessController(BusinessService businessService) {
    this.businessService = businessService;
  }

  /**
   * Get Business By status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllBusiness() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(businessService.getAllBusiness());
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Business getAllBusiness error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get Business By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBusinessById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(businessService.getBusinessById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Business getBusinessById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add Business
   * @param businessRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addBusiness(@RequestBody BusinessRequest businessRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Business business = businessRequest.requestToBusiness(businessRequest);
      response.setData(businessService.saveBusiness(business));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Business addBusiness error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Business
   * @param id
   * @param businessRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBusiness(@PathVariable Long id,
      @RequestBody BusinessRequest businessRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Business> business = businessService.getBusinessById(id);
      if (business.isPresent()) {
        Business updatedBusiness = businessRequest.requestToBusiness(businessRequest);
        updatedBusiness.setId(id);
        response.setData(businessService.saveBusiness(updatedBusiness));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(business);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Business updateBusiness error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Soft Delete Business
   * @param id
   * @return
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> patchBusiness(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Business> business = businessService.getBusinessById(id);
      if (business.isPresent()) {
        Business updatedBusiness = business.get();
        updatedBusiness.setId(id);
        updatedBusiness.setStatus("deleted");
        response.setData(businessService.deleteBusiness(updatedBusiness));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(business);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Business deleteBusiness error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
