package me.karun;

import static me.karun.BeanManager.beanManager;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    port(beanManager().config().getInt("server.port"));

    final OrderController controller = beanManager().orderController();

    post("/order", "application/json", controller::postOrder);
    get("/order/:id", "application/json", controller::getOrder);
  }
}

