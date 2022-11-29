package com.example.btltute.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {

  private String title;

  private String author;

  private String description;

  private LocalDate releaseDate;

  private Integer pageNumber;

  private String category;
}
