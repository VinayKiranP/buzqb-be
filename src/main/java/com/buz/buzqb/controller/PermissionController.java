package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.PermissionRequest;
import com.buz.buzqb.entity.Permission;
import com.buz.buzqb.service.PermissionService;
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
@RequestMapping(Constants.V1_URI + Constants.PERMISSION_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class PermissionController {

  private final PermissionService permissionService;
  public static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class.getName());

  @Autowired
  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  /**
   * Get Permission By Status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllPermission() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      long startTime = System.currentTimeMillis();
      response.setData(permissionService.getAllPermission());
      long endTime = System.currentTimeMillis();
      LOGGER.info(Constants.TimeTakenToExecute+"getAllPermission: {}", endTime - startTime);
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getAllPermission error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get Permission By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getPermissionById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(permissionService.getPermissionById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getPermissionById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add Permission
   * @param permissionRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addPermission(@RequestBody PermissionRequest permissionRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Permission permission = permissionRequest.requestToPermission(permissionRequest);
      response.setData(permissionService.savePermission(permission));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"addPermission error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Permission
   * @param id
   * @param permissionRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updatePermission(@PathVariable Long id,
      @RequestBody PermissionRequest permissionRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Permission> permission = permissionService.getPermissionById(id);
      if (permission.isPresent()) {
        Permission updatedPermission = permissionRequest.requestToPermission(permissionRequest);
        updatedPermission.setId(id);
        response.setData(permissionService.updatePermission(updatedPermission));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(permission);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"updateBusiness error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
