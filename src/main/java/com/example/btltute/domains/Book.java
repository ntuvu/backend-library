package com.example.btltute.domains;

import com.example.btltute.models.BookCreateDTO;
import java.time.LocalDate;
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
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "description")
  private String description;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  @Column(name = "page_number")
  private Integer pageNumber;

  @Column(name = "category")
  private String category;

  @Column(name = "total")
  private Integer total;

  @Column(name = "cost")
  private Double cost;

  public Book(BookCreateDTO dto) {
    this.title = dto.getTitle();
    this.author = dto.getAuthor();
    this.description = dto.getDescription();
    this.releaseDate = dto.getReleaseDate();
    this.pageNumber = dto.getPageNumber();
    this.category = dto.getCategory();
    this.total = dto.getTotal();
    this.cost = dto.getCost();
  }

  public void replace(BookCreateDTO dto) {
    this.title = dto.getTitle();
    this.author = dto.getAuthor();
    this.description = dto.getDescription();
    this.releaseDate = dto.getReleaseDate();
    this.pageNumber = dto.getPageNumber();
    this.category = dto.getCategory();
    this.total = dto.getTotal();
    this.cost = dto.getCost();
  }
}
