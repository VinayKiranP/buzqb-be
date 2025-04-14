package com.buz.buzqb.controller;

import com.buz.buzqb.common.Constants;
import com.buz.buzqb.common.ErrorDto;
import com.buz.buzqb.common.ResponseDto;
import com.buz.buzqb.dto.PostRepliesRequest;
import com.buz.buzqb.entity.PostReplies;
import com.buz.buzqb.service.PostRepliesService;
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
@RequestMapping(Constants.V1_URI + Constants.POST_REPLIES_URI)
@SecurityRequirement(name = Constants.SECURITY_SCHEME_NAME)
public class PostRepliesController {

  private final PostRepliesService postRepliesService;
  public static final Logger LOGGER = LoggerFactory.getLogger(PostRepliesController.class.getName());

  @Autowired
  public PostRepliesController(PostRepliesService postRepliesService) {
    this.postRepliesService = postRepliesService;
  }

  /**
   * Get PostReplies By Status
   * @return
   */
  @GetMapping("/{postId}")
  public ResponseEntity<ResponseDto> getAllPostReplies(@PathVariable Long postId) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      long startTime = System.currentTimeMillis();
      response.setData(postRepliesService.findAllByPostIdOrderByCreatedByDesc(postId));
      long endTime = System.currentTimeMillis();
      LOGGER.info(Constants.TIME_TAKEN_TO_EXECUTE +"getAllPostReplies: {}", endTime - startTime);
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"getAllPostReplies error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Get PostReplies By Id
   * @param id
   * @return
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<ResponseDto> getPostRepliesById(@PathVariable Long id) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      response.setData(postRepliesService.getPostRepliesById(id));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"getPostRepliesById error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Add PostReplies
   * @param postRepliesRequest
   * @return
   */
  @PostMapping
  public ResponseEntity<ResponseDto> addPostReplies(@RequestBody PostRepliesRequest postRepliesRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      PostReplies postReplies = postRepliesRequest.requestToPostReplies(postRepliesRequest);
      response.setData(postRepliesService.savePostReplies(postReplies));
      response.setSuccess(true);
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"addPostReplies error:{}, exception:{}", httpStatusCode,
          ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }

  /**
   * Put PostReplies
   * @param id
   * @param postRepliesRequest
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<ResponseDto> updatePostReplies(@PathVariable Long id,
      @RequestBody PostRepliesRequest postRepliesRequest) {
    ResponseDto response = new ResponseDto();
    HttpStatus httpStatusCode = HttpStatus.OK;

    try {
      Optional<PostReplies> postReplies = postRepliesService.getPostRepliesById(id);
      if (postReplies.isPresent()) {
        PostReplies updatedPostReplies = postRepliesRequest.requestToPostReplies(postRepliesRequest);
        updatedPostReplies.setId(id);
        response.setData(postRepliesService.updatePostReplies(updatedPostReplies));
        response.setSuccess(true);
      } else {
        httpStatusCode = HttpStatus.NO_CONTENT;
        response.setData(postReplies);
      }
    } catch (Exception e) {
      httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
      response.setErrors(ErrorDto.getErrorFromException(e));
      response.setSuccess(false);
      LOGGER.error(Constants.ERROR_IN +"updatePostReplies error:{}, exception:{}",
          httpStatusCode, ErrorDto.getErrorFromException(e));
    }
    return new ResponseEntity<>(response, httpStatusCode);
  }
}
