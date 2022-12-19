package com.example.btltute.models;

import com.example.btltute.domains.Book;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

  private Long id;

  private String title;

  private String author;

  private String description;

  private LocalDate releaseDate;

  private Integer pageNumber;

  private String category;

  private Integer total;

  private Double cost;

  private Long imageId;

  public BookResponseDTO(Book book, Long imageId) {
    this.id = book.getId();
    this.title = book.getTitle();
    this.author = book.getAuthor();
    this.description = book.getDescription();
    this.releaseDate = book.getReleaseDate();
    this.pageNumber = book.getPageNumber();
    this.category = book.getCategory();
    this.total = book.getTotal();
    this.cost = book.getCost();
    this.imageId = imageId;
  }

  public BookResponseDTO(Book book) {
    this.id = book.getId();
    this.title = book.getTitle();
    this.author = book.getAuthor();
    this.description = book.getDescription();
    this.releaseDate = book.getReleaseDate();
    this.pageNumber = book.getPageNumber();
    this.category = book.getCategory();
    this.total = book.getTotal();
    this.cost = book.getCost();
    this.imageId = imageId;
  }
}
