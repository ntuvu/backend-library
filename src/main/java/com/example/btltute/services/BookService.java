package com.example.btltute.services;

import com.example.btltute.domains.Book;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.BookCreateDTO;
import com.example.btltute.models.BookResponseDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

  void createBook(BookCreateDTO dto) throws CustomException;

  void updateBook(BookCreateDTO dto, Long id) throws CustomException;

  void deleteBook(Long id) throws CustomException;

  Page<BookResponseDTO> listBook(Pageable pageable) throws CustomException;

  BookResponseDTO getBook(Long id) throws CustomException;

  List<Book> test() throws CustomException;
}
