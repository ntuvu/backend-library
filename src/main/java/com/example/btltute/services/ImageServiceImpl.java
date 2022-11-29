package com.example.btltute.services;

import com.example.btltute.domains.Book;
import com.example.btltute.domains.Image;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.repositories.BookRepository;
import com.example.btltute.repositories.ImageRepository;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final ImageRepository imageRepository;
  private final BookRepository bookRepository;

  @Override
  public void store(MultipartFile file, Long bookId) throws CustomException, IOException {
    Optional<Book> bookOptional = bookRepository.findById(bookId);
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    Image image = new Image(bookId, fileName, file.getContentType(), file.getBytes());
    imageRepository.save(image);
  }

  @Override
  public Image getImage(Long id) throws CustomException {
    Optional<Image> imageOptional = imageRepository.findById(id);
    if (imageOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.IMAGE_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.IMAGE_NOT_EXIST));
    }
    return imageOptional.get();
  }
}
