package me.karun;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
  private Map<Integer, Order> orders;
  private Gson gson = new Gson();
  @Mock
  private Request request;
  @Mock
  private Response response;
  private OrderController controller;

  @Before
  public void setup() {
    orders = new HashMap<>();
    controller = new OrderController(orders);
  }

  @Test
  public void postOrder_whenAnOrderIsPosted_thenTheResponseIsSuccessful() {
    final Order expected = new Order(1, "Banana");
    when(request.body()).thenReturn(gson.toJson(expected));

    final String httpResponse = controller.postOrder(request, this.response);

    assertThat(httpResponse).isEqualTo("1");
    assertThat(orders.get(1)).isEqualToComparingFieldByField(expected);
    verify(response).status(200);
  }

  @Test
  public void postOrder_whenAnEmptyBodyIsPosted_thenTheResponseIsAFailure() {
    when(request.body()).thenReturn("");

    final String httpResponse = controller.postOrder(request, this.response);

    assertThat(httpResponse).isEqualTo("{\"message\":\"Invalid request\"}");
    verify(response).status(400);
  }

  @Test
  public void getOrder_whenAnOrderHasBeenPlacedWithTheId_thenTheOrderIsReturned() {
    final Order expected = new Order(1, "Banana");
    orders.put(expected.getId(), expected);
    when(request.params("id")).thenReturn(expected.getId().toString());

    final String httpResponse = controller.getOrder(request, response);

    assertThat(httpResponse).isEqualTo(gson.toJson(expected));
  }

  @Test
  public void getOrder_whenAnOrderHasBeenNotPlacedWithTheId_thenTheOrderIsReturned() {
    final Order expected = new Order(1, "Banana");
    when(request.params("id")).thenReturn(expected.getId().toString());

    final String httpResponse = controller.getOrder(request, response);

    assertThat(httpResponse).isEqualTo("null");
  }
}