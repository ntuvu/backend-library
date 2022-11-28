package com.example.btltute.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomException extends Exception {
  private String messageKey;
  private String message;

  public CustomException(String messageKey) {
    this.messageKey = messageKey;
  }

  public String getMessage() {
    if (this.message != null) {
      return message;
    }
    if (this.messageKey != null) {
      this.message = String.format(ExceptionUtils.messages.get(this.messageKey));
    }
    return null;
  }
}
