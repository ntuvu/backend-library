package com.example.btltute.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "image")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "file_type")
  private String fileType;

  @Lob
  @Column(name = "image_file")
  private byte[] imageFile;

  public Image(Long bookId, String fileName, String fileType, byte[] imageFile) {
    this.bookId = bookId;
    this.fileName = fileName;
    this.fileType = fileType;
    this.imageFile = imageFile;
  }
}
