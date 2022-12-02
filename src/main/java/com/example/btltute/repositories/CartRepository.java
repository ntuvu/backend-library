package com.example.btltute.repositories;

import com.example.btltute.domains.Cart;
import com.example.btltute.models.CartResponseDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

  boolean existsByBookIdAndUserId(Long bookId, Long userId);

  @Query(
      value =
          "select new com.example.btltute.models.CartResponseDTO(c, b) "
              + "from Cart c "
              + "join Book b on c.bookId = b.id "
              + "where (:#{#userId} = c.userId) ")
  List<CartResponseDTO> findCartList(@Param(value = "userId") Long userId);
}
