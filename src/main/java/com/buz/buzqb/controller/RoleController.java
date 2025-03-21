package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.controller.auth.UserController;
import com.buz.buzqb.dto.RoleRequest;
import com.buz.buzqb.entity.Business;
import com.buz.buzqb.entity.Role;
import com.buz.buzqb.service.RoleService;
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
@RequestMapping(Constants.V1_URI + Constants.ROLE_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class RoleController {

  private final RoleService roleService;
  public static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class.getName());

  @Autowired
  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }
  @Autowired
  private UserController userController;

  /**
   * Get Role By Status
   * @return
   */
  @GetMapping
  public ResponseEntity<ResponseDto> getAllRole() {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      long startTime = System.currentTimeMillis();
      Business business = userController.authenticatedBusiness();
      LOGGER.info(Constants.USERS_URI+"user: {}", business);
      response.setData(roleService.getAllRole());
      long endTime = System.currentTimeMillis();
      LOGGER.info(Constants.TimeTakenToExecute+"getAllRole: {}", endTime - startTime);
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getAllRole error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get Role By Id
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getRoleById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(roleService.getRoleById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"getRoleById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add Role
   * @param roleRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addRole(@RequestBody RoleRequest roleRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Role role = roleRequest.requestToRole(roleRequest);
      response.setData(roleService.saveRole(role));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ErrorIn+"addRole error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put Role
   * @param id
   * @param roleRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updateRole(@PathVariable Long id,
      @RequestBody RoleRequest roleRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<Role> role = roleService.getRoleById(id);
      if (role.isPresent()) {
        Role updatedRole = roleRequest.requestToRole(roleRequest);
        updatedRole.setId(id);
        response.setData(roleService.updateRole(updatedRole));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(role);
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
