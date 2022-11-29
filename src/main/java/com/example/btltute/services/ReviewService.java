package com.example.btltute.services;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.ReviewCreateDTO;
import com.example.btltute.models.ReviewResponseDTO;
import java.util.List;

public interface ReviewService {

  void createReview(ReviewCreateDTO dto) throws CustomException;

  List<ReviewResponseDTO> getReviewList(Long bookId) throws CustomException;
}
