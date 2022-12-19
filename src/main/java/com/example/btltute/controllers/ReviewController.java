package com.example.btltute.controllers;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.ErrorDTO;
import com.example.btltute.models.ReviewCreateDTO;
import com.example.btltute.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<Object> createReview(@RequestBody ReviewCreateDTO dto) {
    try {
      reviewService.createReview(dto);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<Object> getReviewList(@PathVariable(value = "bookId") Long bookId) {
    try {
      return new ResponseEntity<>(reviewService.getReviewList(bookId), HttpStatus.OK);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
