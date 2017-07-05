package me.karun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qmetric.spark.authentication.AuthenticationDetails;

import java.util.HashMap;
import java.util.Optional;

import static java.util.Optional.empty;

class BeanManager {
  private static Optional<BeanManager> beanManager = empty();
  private final OrderController orderController;
  private final Config config;
  private final InfoEndpointBuilder infoEndpointBuilder;
  private final Gson gson;

  private BeanManager() {
    gson = new GsonBuilder().create();
    orderController = new OrderController(new HashMap<>(), gson);
    config = new Config("config", "git");
    infoEndpointBuilder = new InfoEndpointBuilder(config, gson);
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

  AuthenticationDetails authDetails() {
    return new AuthenticationDetails(config.get("auth.username").get(), config.get("auth.password").get());
  }

  OrderController orderController() {
    return orderController;
  }

  InfoEndpointBuilder infoEndpointBuilder() {
    return infoEndpointBuilder;
  }
}
