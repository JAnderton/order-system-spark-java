package me.karun;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Slf4j
class OrderController {

  private final Map<Integer, Order> orders;
  private final Gson gson;

  OrderController() {
    this.orders = new HashMap<>();
    this.gson = new Gson();
  }

  String postOrder(final Request request, final Response response) {
    final String requestMessage = request.body();
    log.info("Order {} was posted", requestMessage);

    final Order order = gson.fromJson(requestMessage, Order.class);
    orders.put(order.getId(), order);

    response.status(200);
    log.debug("Returning status code {} for request {}", response.status(), requestMessage);
    return order.getId().toString();
  }

  String getOrder(final Request request, final Response response) {
    final String id = request.params("id");
    log.info("Fetching order {}", id);

    return gson.toJson(orders.get(parseInt(id)));
  }
}

