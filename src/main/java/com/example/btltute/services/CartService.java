package com.example.btltute.services;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.CartCreateDTO;
import com.example.btltute.models.CartResponseDTO;
import com.example.btltute.models.CartUpdateDTO;
import java.util.List;

public interface CartService {

  void createCart(CartCreateDTO dto) throws CustomException;

  void updateCart(CartUpdateDTO dto, Long id) throws CustomException;

  void deleteCart(Long id) throws CustomException;

  List<CartResponseDTO> getCartOfCurrentUser() throws CustomException;
}
