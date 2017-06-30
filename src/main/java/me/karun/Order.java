package me.karun;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class Order {
  private final Integer id;
  private final String name;
}
