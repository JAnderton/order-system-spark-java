package me.karun;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.get;

public class App {
  public static void main(String[] args) {
    port(8080);
    final OrderController controller = new OrderController();

    post("/order","application/json", controller::postOrder);
    get("/order/:id", "application/json", controller::getOrder);
  }
}

