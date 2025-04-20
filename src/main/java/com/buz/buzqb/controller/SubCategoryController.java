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
      long startTime = System.currentTimeMillis();
      response.setData(subCategoryService.getAllSubCategory());
      long endTime = System.currentTimeMillis();
      LOGGER.info(Constants.TIME_TAKEN_TO_EXECUTE +"getAllSubCategory: {}", endTime - startTime);
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"getAllSubCategory error:{}, exception:{}",
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
      LOGGER.error(Constants.ERROR_IN +"getSubCategoryById error:{}, exception:{}",
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
      LOGGER.error(Constants.ERROR_IN +"addSubCategory error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put SubCategory
   * @param id
   * @param subCategoryRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateSubCategory(@PathVariable Long id,
      @RequestBody SubCategoryRequest subCategoryRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<SubCategory> subCategory = subCategoryService.getSubCategoryById(id);
      if (subCategory.isPresent()) {
        SubCategory updatedSubCategory = subCategoryRequest.requestToSubCategory(subCategoryRequest);
        updatedSubCategory.setId(id);
        response.setData(subCategoryService.updateSubCategory(updatedSubCategory));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(subCategory);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"updateSubCategory error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
