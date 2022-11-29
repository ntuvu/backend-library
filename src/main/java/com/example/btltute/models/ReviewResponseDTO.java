package com.example.btltute.models;

import com.example.btltute.domains.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {

  private Long bookId;

  private String content;

  private Integer star;

  private String username;

  public ReviewResponseDTO(Review review) {
    this.bookId = review.getBookId();
    this.content = review.getContent();
    this.star = review.getStar();
    this.username = review.getUsername();
  }
}
