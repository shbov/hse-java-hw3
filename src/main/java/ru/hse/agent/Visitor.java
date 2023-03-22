package ru.hse.agent;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuIn;

@Slf4j
public class Visitor extends Agent {
  public Visitor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message message) throws Exception {
    // 1. Актуальное меню: исключительно блюда и напитки, которые могут быть приготовлены за заданное нормативное время
    if (message instanceof CreateOrderIn) {
      log.info("Примерное время готовки заказа " + ((CreateOrderIn) message).minute + " мин.");
    } else if (message instanceof SendMenuIn) {
      List<Dish> dishes = ((SendMenuIn) message).dishes;
    } else {
      System.out.println("Message not acceptable " + message.getClass().toString());
    }
  }
}
