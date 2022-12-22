package com.example.btltute.services;

import com.example.btltute.domains.Book;
import com.example.btltute.domains.Cart;
import com.example.btltute.domains.Image;
import com.example.btltute.domains.User;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.CartCreateDTO;
import com.example.btltute.models.CartResponseDTO;
import com.example.btltute.models.CartUpdateDTO;
import com.example.btltute.repositories.BookRepository;
import com.example.btltute.repositories.CartRepository;
import com.example.btltute.repositories.ImageRepository;
import com.example.btltute.repositories.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;
  private final ImageRepository imageRepository;

  @Override
  public void createCart(CartCreateDTO dto) throws CustomException {
    Optional<Book> bookOptional = bookRepository.findById(dto.getBookId());
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    if (dto.getAmount() <= 0) {
      throw new CustomException(
          ExceptionUtils.AMOUNT_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.AMOUNT_NOT_VALID));
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    if (cartRepository.existsByBookIdAndUserId(dto.getBookId(), user.getId())) {
      throw new CustomException(
          ExceptionUtils.CAR_EXISTED, ExceptionUtils.messages.get(ExceptionUtils.CAR_EXISTED));
    }
    Book book = bookOptional.get();
    if (book.getTotal() < dto.getAmount()) {
      throw new CustomException(
          ExceptionUtils.AMOUNT_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.AMOUNT_NOT_VALID));
    }
    Cart cart = new Cart(dto);
    cart.setUserId(user.getId());
    cartRepository.save(cart);
  }

  @Override
  public void updateCart(CartUpdateDTO dto, Long idCart) throws CustomException {
    if (dto.getAmount() <= 0) {
      throw new CustomException(
          ExceptionUtils.AMOUNT_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.AMOUNT_NOT_VALID));
    }
    Optional<Cart> cartOptional = cartRepository.findById(idCart);
    if (cartOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.CART_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.CART_NOT_EXIST));
    }
    Cart cart = cartOptional.get();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    if (!Objects.equals(user.getId(), cart.getUserId())) {
      throw new CustomException(
          ExceptionUtils.USER_NAME_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.USER_NAME_NOT_VALID));
    }
    Optional<Book> bookOptional = bookRepository.findById(cartOptional.get().getBookId());
    if (bookOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.BOOK_ID_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.BOOK_ID_NOT_EXIST));
    }
    if (bookOptional.get().getTotal() < dto.getAmount()) {
      throw new CustomException(
          ExceptionUtils.AMOUNT_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.AMOUNT_NOT_VALID));
    }
    cart.setAmount(dto.getAmount());
    cartRepository.save(cart);
  }

  @Override
  public void deleteCart(Long id) throws CustomException {
    Optional<Cart> cartOptional = cartRepository.findById(id);
    if (cartOptional.isEmpty()) {
      throw new CustomException(
          ExceptionUtils.CART_NOT_EXIST,
          ExceptionUtils.messages.get(ExceptionUtils.CART_NOT_EXIST));
    }
    Cart cart = cartOptional.get();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    if (!Objects.equals(user.getId(), cart.getUserId())) {
      throw new CustomException(
          ExceptionUtils.USER_NAME_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.USER_NAME_NOT_VALID));
    }
    cartRepository.deleteById(id);
  }

  @Override
  public List<CartResponseDTO> getCartOfCurrentUser() throws CustomException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return cartRepository.findCartList(user.getId());
  }
}
