package ru.hse.agent;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrder;
import ru.hse.message.supervisor.SendMenu;

@Slf4j
public class Visitor extends Agent {
  public Visitor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message message) throws Exception {
    if (message instanceof CreateOrder) {
      System.out.println("Estimated time of order " + ((CreateOrder) message).minute);
    } else if (message instanceof SendMenu) {
      List<Dish> dishes = ((SendMenu) message).dishes;

    } else {
      System.out.println("Message not acceptable " + message.getClass().toString());
        }

    }
}
