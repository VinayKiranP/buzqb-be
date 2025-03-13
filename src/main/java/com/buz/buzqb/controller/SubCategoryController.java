package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.SubCategoryRequest;
import com.buz.buzqb.entity.SubCategory;
import com.buz.buzqb.service.SubCategoryService;
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
@RequestMapping(Constants.V1_URI + Constants.SUB_CATEGORY_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class SubCategoryController {

  private final SubCategoryService subCategoryService;
  public static final Logger LOGGER = LoggerFactory.getLogger(SubCategoryController.class.getName());

  @Autowired
  public SubCategoryController(SubCategoryService subCategoryService) {
    this.subCategoryService = subCategoryService;
  }

  /**
   * Get SubCategory By Status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllSubCategory() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(subCategoryService.getAllSubCategory());
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting SubCategory getAllSubCategory error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get SubCategory By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getSubCategoryById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(subCategoryService.getSubCategoryById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting SubCategory getSubCategoryById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add SubCategory
   * @param subCategoryRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      SubCategory subCategory = subCategoryRequest.requestToSubCategory(subCategoryRequest);
      response.setData(subCategoryService.saveSubCategory(subCategory));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Brand addBrand error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Brand
   * @param id
   * @param subCategoryRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateBrand(@PathVariable Long id,
      @RequestBody SubCategoryRequest subCategoryRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<SubCategory> business = subCategoryService.getSubCategoryById(id);
      if (business.isPresent()) {
        SubCategory updatedBrand = subCategoryRequest.requestToSubCategory(subCategoryRequest);
        updatedBrand.setId(id);
        response.setData(subCategoryService.updateSubCategory(updatedBrand));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(business);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error("error in Getting Brand updateBrand error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
