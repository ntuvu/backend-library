package com.example.btltute.services;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.BookCreateDTO;
import com.example.btltute.models.BookResponseDTO;
import java.util.List;

public interface BookService {

  void createBook(BookCreateDTO dto) throws CustomException;

  void updateBook(BookCreateDTO dto, Long id) throws CustomException;

  void deleteBook(Long id) throws CustomException;

  List<BookResponseDTO> listBook() throws CustomException;

  BookResponseDTO getBook(Long id) throws CustomException;
}
