package ru.hse;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;
import ru.hse.agent.SuperVisor;
import ru.hse.agent.Visitor;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuIn;
import ru.hse.utilities.AgentUtility;

@Slf4j
public class ConnectionExample {
  public static void main(String[] args) {
    SuperVisor superVisor = new SuperVisor(AgentUtility.generateID(SuperVisor.class));
    SuperVisor.start(superVisor);

    Visitor visitor1 = new Visitor(AgentUtility.generateID(Visitor.class), superVisor);
    Visitor.start(visitor1);

    List<Dish> dishes = new CopyOnWriteArrayList<>();
    SendMenuIn message = new SendMenuIn();
    CreateOrderIn order = new CreateOrderIn(dishes);

    visitor1.registerMessage(message);
    superVisor.registerMessage(new Message());
    visitor1.registerMessage(order);
  }
}
