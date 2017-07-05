package me.karun;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spark.Request;
import spark.Response;

import java.util.Map;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
@RequiredArgsConstructor
class OrderController {

  private final Map<Integer, Order> orders;
  private final Gson gson;

  String postOrder(final Request request, final Response response) {
    final String requestMessage = request.body();
    log.info("Order \"{}\" was posted", requestMessage);

    final Optional<Order> order = Optional.ofNullable(gson.fromJson(requestMessage, Order.class));
    if (!order.isPresent()) {
      response.status(HTTP_BAD_REQUEST);
      return gson.toJson(new HttpError("Invalid request"));
    }

    orders.put(order.get().getId(), order.get());

    response.status(HTTP_OK);
    log.debug("Returning status code {} for request {}", response.status(), requestMessage);
    return gson.toJson(order.get());
  }

  String getOrder(final Request request, final Response response) {
    final String id = request.params("id");
    log.info("Fetching order {}", id);

    final Order order = orders.get(parseInt(id));
    return gson.toJson(order);
  }
}