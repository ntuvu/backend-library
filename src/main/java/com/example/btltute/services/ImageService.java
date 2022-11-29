package com.example.btltute.services;

import com.example.btltute.domains.Image;
import com.example.btltute.exceptions.CustomException;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

  void store(MultipartFile file, Long bookId) throws CustomException, IOException;

  Image getImage(Long id) throws CustomException;
}
