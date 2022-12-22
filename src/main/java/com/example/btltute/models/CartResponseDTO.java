package com.example.btltute.models;

import com.example.btltute.domains.Book;
import com.example.btltute.domains.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

  private Long id;
  private Long bookId;

  private Long userId;

  private Integer amount;

  private BookResponseDTO book;

  private Long imageId;

  public CartResponseDTO(Cart cart, Book book, Long imageId) {
    this.id = cart.getId();
    this.bookId = cart.getBookId();
    this.userId = cart.getUserId();
    this.amount = cart.getAmount();
    this.book = new BookResponseDTO(book);
    this.imageId = imageId;
  }
}
