package me.karun;

import java.util.HashMap;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    port(8080);
    final OrderController controller = new OrderController(new HashMap<>());

    post("/order", "application/json", controller::postOrder);
    get("/order/:id", "application/json", controller::getOrder);
  }
}

