package com.example.btltute.services;

import com.example.btltute.domains.Book;
import com.example.btltute.domains.Review;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.ReviewCreateDTO;
import com.example.btltute.models.ReviewResponseDTO;
import com.example.btltute.repositories.BookRepository;
import com.example.btltute.repositories.ReviewRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final BookRepository bookRepository;

  @Override
  public void createReview(ReviewCreateDTO dto) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(dto.getBookId());
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    if (dto.getStar() <= 0 || dto.getStar() > 5) {
      throw new CustomException(
          ExceptionUtils.STAR_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.STAR_NOT_VALID));
    }
    Review review = new Review(dto);
    review.setUsername(username);
    reviewRepository.save(review);
  }

  @Override
  public List<ReviewResponseDTO> getReviewList(Long bookId) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(bookId);
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    List<Review> reviewList = reviewRepository.findAllByBookId(bookId);
    return reviewList.stream().map(ReviewResponseDTO::new).toList();
  }
}
