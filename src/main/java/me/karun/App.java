package me.karun;

import lombok.extern.slf4j.Slf4j;

import static me.karun.BeanManager.beanManager;
import static spark.Spark.*;

@Slf4j
public class App {
  public static void main(String[] args) {
    port(beanManager().config().getInt("server.port"));

    initExceptionHandler((e) -> {
      log.error("Unable to start server", e);
      stop();
      log.error("Stopped web server due to startup failure. Check logs for details.");
    });

    final OrderController controller = beanManager().orderController();
    post("/order", "application/json", controller::postOrder);
    get("/order/:id", "application/json", controller::getOrder);
  }
}

