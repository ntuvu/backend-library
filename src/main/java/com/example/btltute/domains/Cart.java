package com.example.btltute.domains;

import com.example.btltute.models.CartCreateDTO;
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
@Table(name = "cart")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "book_id")
  private Long bookId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "amount")
  private Integer amount;

  public Cart(CartCreateDTO dto) {
    this.bookId = dto.getBookId();
    this.amount = dto.getAmount();
  }
}
