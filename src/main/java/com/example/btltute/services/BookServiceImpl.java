package com.example.btltute.services;

import com.example.btltute.domains.Book;
import com.example.btltute.domains.Image;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.BookCreateDTO;
import com.example.btltute.models.BookResponseDTO;
import com.example.btltute.repositories.BookRepository;
import com.example.btltute.repositories.ImageRepository;
import com.example.btltute.repositories.TestRepositoryCustom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final ImageService imageService;
  private final ImageRepository imageRepository;
  private final TestRepositoryCustom testRepositoryCustom;

  @Override
  public void createBook(BookCreateDTO dto) throws CustomException {
    validate(dto);
    bookRepository.save(new Book(dto));
  }

  @Override
  public void updateBook(BookCreateDTO dto, Long id) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    validate(dto);
    Book book = bookOptional.get();
    book.replace(dto);
    bookRepository.save(book);
  }

  @Override
  public void deleteBook(Long id) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    bookRepository.deleteById(id);
  }

  @Override
  public Page<BookResponseDTO> listBook(Pageable pageable) throws CustomException {
    return bookRepository.findAllBooks(pageable);
  }

  @Override
  public BookResponseDTO getBook(Long id) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(id);
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    BookResponseDTO response = new BookResponseDTO(bookOptional.get());
    Image image = imageRepository.findImageByBookId(response.getId());
    response.setImageId(image.getId());
    return response;
  }

  private void validate(BookCreateDTO dto) throws CustomException {
    if (StringUtils.isBlank(dto.getTitle())) {
      throw new CustomException(
          ExceptionUtils.BOOK_TITLE_REQUIRED,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_TITLE_REQUIRED));
    }
    if (StringUtils.isBlank(dto.getAuthor())) {
      throw new CustomException(
          ExceptionUtils.BOOK_AUTHOR_REQUIRED,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_AUTHOR_REQUIRED));
    }
    if (StringUtils.isBlank(dto.getCategory())) {
      throw new CustomException(
          ExceptionUtils.BOOK_CATEGORY_REQUIRED,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_CATEGORY_REQUIRED));
    }
    if (StringUtils.isBlank(dto.getAuthor())) {
      throw new CustomException(
          ExceptionUtils.BOOK_AUTHOR_REQUIRED,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_AUTHOR_REQUIRED));
    }
    if (dto.getPageNumber() == null || dto.getPageNumber() <= 0) {
      throw new CustomException(
          ExceptionUtils.BOOK_PAGE_NUMBER_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_PAGE_NUMBER_NOT_VALID));
    }
    if (dto.getTotal() <= 0) {
      throw new CustomException(
          ExceptionUtils.TOTAL_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.TOTAL_NOT_VALID));
    }
    if (dto.getCost() <= 0) {
      throw new CustomException(
          ExceptionUtils.TOTAL_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.TOTAL_NOT_VALID));
    }
  }

  @Override
  public List<Book> test() throws CustomException {
    return testRepositoryCustom.getAllBook();
  }
}
