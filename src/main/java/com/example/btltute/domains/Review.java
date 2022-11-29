package com.example.btltute.domains;

import com.example.btltute.models.ReviewCreateDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "content")
  private String content;

  @Column(name = "star")
  private Integer star;

  @Column(name = "username")
  private String username;

  public Review(ReviewCreateDTO dto) {
    this.bookId = dto.getBookId();
    this.content = dto.getContent();
    this.star = dto.getStar();
  }
}
