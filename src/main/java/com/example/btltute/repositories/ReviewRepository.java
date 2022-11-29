package com.example.btltute.repositories;

import com.example.btltute.domains.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAllByBookId(Long bookId);
}
