package com.example.btltute.repositories;

import com.example.btltute.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  
}
