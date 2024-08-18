package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.BusinessRequest;
import com.buz.buzqb.dto.auth.AuthenticatedUser;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(Constants.V1_URI + Constants.BUSINESS_URI)
public class BusinessController {

  private final BusinessService businessService;
  public static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class.getName());

  @Autowired
  private BusinessController(BusinessService businessService) {
    this.businessService = businessService;
  }

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

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getBusinessById(@PathVariable Integer id) throws Exception {
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

  @PostMapping
  public ResponseEntity<ResponseDto> addBusiness(@RequestBody BusinessRequest businessRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(businessService.saveBusiness(businessRequest));
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

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBusiness(@PathVariable Integer id,
      @RequestBody BusinessRequest businessRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Business> business = businessService.getBusinessById(id);
      if (business.isPresent()) {
        Business updatedBusiness = businessRequest.requestToBusiness(businessRequest);
        updatedBusiness.setId(id);
        response.setData(businessService.updateBusiness(updatedBusiness));
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

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDto> patchBusiness(@PathVariable Integer id) {
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
