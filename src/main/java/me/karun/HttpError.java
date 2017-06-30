package me.karun;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
class HttpError {
  private final String message;
}
