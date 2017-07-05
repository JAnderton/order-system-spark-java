package me.karun;

import com.qmetric.spark.authentication.AuthenticationDetails;
import com.qmetric.spark.authentication.BasicAuthenticationFilter;
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
    before(authFilter("/order/*", beanManager().authDetails());
    after((request, response) -> response.type("application/json"));

    final OrderController controller = beanManager().orderController();
    path("/order", () -> {
      post("", "application/json", controller::postOrder);
      get("/:id", "application/json", controller::getOrder);
    });

    get("/info", (req, res) -> beanManager().infoEndpointBuilder().fetchAppInfo());
    get("/health", (req, res) -> "{\"status\":\"UP\"}");
  }

  private static BasicAuthenticationFilter authFilter(final String path, final AuthenticationDetails authDetails) {
    return new BasicAuthenticationFilter(path, authDetails);
  }
}

