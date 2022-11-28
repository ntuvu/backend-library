package com.example.btltute.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {

  public static final String BOOK_TITLE_REQUIRED = "BOOK_TITLE_REQUIRED";
  public static final String BOOK_CATEGORY_REQUIRED = "BOOK_CATEGORY_REQUIRED";
  public static final String BOOK_AUTHOR_REQUIRED = "BOOK_AUTHOR_REQUIRED";
  public static final String BOOK_PAGE_NUMBER_NOT_VALID = "BOOK_PAGE_NUMBER_NOT_VALID";
  public static final String E_INTERNAL_SERVER = "E_INTERNAL_SERVER";
  public static final String BOOK_ID_NOT_EXIST = "BOOK_ID_NOT_EXIST";
  public static final String ACCOUNT_EXISTED = "ACCOUNT_EXISTED";
  public static final String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
  public static final String USER_NAME_NOT_VALID = "USER_NAME_NOT_VALID";
  public static final String PASSWORD_NOT_VALID = "PASSWORD_NOT_VALID";
  public static final String EMAIL_NOT_VALID = "EMAIL_NOT_VALID";
  public static final String ROLE_EXISTED = "ROLE_EXISTED";

  public static final Map<String, String> messages;

  static {
    messages = new HashMap<>();
    messages.put(ExceptionUtils.BOOK_TITLE_REQUIRED, "Tiêu đề không được để trống");
    messages.put(ExceptionUtils.BOOK_CATEGORY_REQUIRED, "Danh mục không được để trống");
    messages.put(ExceptionUtils.BOOK_AUTHOR_REQUIRED, "Tác giả không được để trống");
    messages.put(ExceptionUtils.BOOK_PAGE_NUMBER_NOT_VALID, "Số trang không hợp lệ");
    messages.put(ExceptionUtils.BOOK_ID_NOT_EXIST, "ID không tồn tại");
    messages.put(ExceptionUtils.ACCOUNT_EXISTED, "Tài khoản đã tồn tại");
    messages.put(ExceptionUtils.ACCOUNT_NOT_EXIST, "Tài khoản không tồn tại");
    messages.put(ExceptionUtils.USER_NAME_NOT_VALID, "Tài khoản không hợp lệ");
    messages.put(ExceptionUtils.PASSWORD_NOT_VALID, "Mật khẩu không hợp lệ");
    messages.put(ExceptionUtils.EMAIL_NOT_VALID, "Email không hợp lệ");
    messages.put(ExceptionUtils.ROLE_EXISTED, "Role đã tồn tại");
  }
}
