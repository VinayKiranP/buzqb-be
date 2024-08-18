package com.buz.buzqb.exception;

import static org.aspectj.bridge.MessageUtil.getMessages;

import com.buz.buzqb.common.Constants;
import com.google.gson.JsonObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  //added for InvalidValuesException, as it's not worked moved if case to handleSecurityException
//  @ExceptionHandler(Exception.class)
//  public void handleConflict(HttpServletResponse response, Exception exception) throws IOException {
//    LOGGER.error("");
//    ResponseStatus responseStatus = AnnotationUtils.findAnnotation(exception.getClass(),
//        ResponseStatus.class);
//    response.setStatus(responseStatus == null ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR
//        : responseStatus.value().value());
//    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//    JsonObject jsonObject = new JsonObject();
//
//    if (exception instanceof InvalidValuesException) {
//      JsonObject jsonDescription = new JsonObject();
//      List<FieldError> fieldErrors = ((MethodArgumentNotValidException) exception).getBindingResult()
//          .getFieldErrors();
//      for (FieldError fieldError : fieldErrors) {
//        jsonDescription.addProperty(fieldError.getField(), fieldError.getDefaultMessage());
//      }
//      jsonObject.add(Constants.DESCRIPTION, jsonDescription);
//      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//    }
//
//    response.getWriter().print(jsonObject);
//  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleSecurityException(Exception exception) {
    ProblemDetail errorDetail = null;

    // TODO send this stack trace to an observability tool
    exception.printStackTrace();

    if (exception instanceof BadCredentialsException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),
          exception.getMessage());
      errorDetail.setProperty("description", "The username or password is incorrect");

      return errorDetail;
    }

    if (exception instanceof AccountStatusException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),
          exception.getMessage());
      errorDetail.setProperty("description", "The account is locked");
    }

    if (exception instanceof AccessDeniedException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),
          exception.getMessage());
      errorDetail.setProperty("description", "You are not authorized to access this resource");
    }

    if (exception instanceof SignatureException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),
          exception.getMessage());
      errorDetail.setProperty("description", "The JWT signature is invalid");
    }

    if (exception instanceof ExpiredJwtException) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),
          exception.getMessage());
      errorDetail.setProperty("description", "The JWT token has expired");
    }

    if (exception instanceof InvalidValuesException) {
      JsonObject jsonDescription = new JsonObject();
      Map<String, String> fieldErrors = ((InvalidValuesException) exception).getMessages();
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(HttpServletResponse.SC_BAD_REQUEST),
          exception.getMessage());
      errorDetail.setProperty(Constants.DESCRIPTION, fieldErrors);
    }

    if (errorDetail == null) {
      errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),
          exception.getMessage());
      errorDetail.setProperty("description", "Unknown internal server error.");
    }

    return errorDetail;
  }
}