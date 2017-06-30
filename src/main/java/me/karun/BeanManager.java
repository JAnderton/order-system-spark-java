package me.karun;

import java.util.HashMap;
import java.util.Optional;

import static java.util.Optional.empty;

class BeanManager {
  private static Optional<BeanManager> beanManager = empty();
  private final OrderController orderController;
  private final Config config;

  private BeanManager() {
    orderController = new OrderController(new HashMap<>());
    config = new Config("config");
  }

  static BeanManager beanManager() {
    if (!beanManager.isPresent()) {
      beanManager = Optional.of(new BeanManager());
    }

    return beanManager.get();
  }

  Config config() {
    return config;
  }

  OrderController orderController() {
    return orderController;
  }
}
