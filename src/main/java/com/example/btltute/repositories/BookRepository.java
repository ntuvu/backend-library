package com.example.btltute.repositories;

import com.example.btltute.domains.Book;
import com.example.btltute.models.BookResponseDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

  @Query(
      value =
          "select new com.example.btltute.models.BookResponseDTO(b, i.id) "
              + "from Book b "
              + "join Image i on b.id = i.bookId ")
  Page<BookResponseDTO> findAllBooks(Pageable pageable);
}
