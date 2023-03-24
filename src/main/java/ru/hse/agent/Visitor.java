package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuIn;
import ru.hse.utilities.AgentUtility;

@Slf4j
public class Visitor extends Agent {
  @Getter
  @JsonProperty("vis_name")
  private String name;

  @Getter
  @JsonProperty("vis_ord_started")
  private Date startOrderDate;

  @Getter
  @JsonProperty("vis_ord_ended")
  private Date endOrderDate;

  @Getter
  @JsonProperty("vis_ord_dishes")
  private List<Dish> dishes; // todo а вот не будет оно так работать, надо подумать, тупое дз
  //  {
  //    "ord_dish_id": 626,
  //    "menu_dish": 28
  //  }

  public Visitor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message message) throws Exception {
    log.info(Thread.currentThread().getName());
    if (message instanceof CreateOrderIn createOrderIn) {
      Order order =
          new Order(
              AgentUtility.generateID(Order.class), this.getSupervisor(), createOrderIn.dishes);
      Order.start(order);

      order.registerMessage(createOrderIn);
    } else if (message instanceof SendMenuIn sendMenuIn) {
      // 1. Актуальное меню: исключительно блюда и напитки, которые
      // могут быть приготовлены за заданное нормативное время

      List<Dish> dishes = sendMenuIn.dishes;
      log.info("Вот наше супер меню: " + dishes.toString());
      // todo: foreach dishes from where?...
    } else {
      System.out.println("Message not acceptable " + message.getClass().toString());
    }
  }
}
