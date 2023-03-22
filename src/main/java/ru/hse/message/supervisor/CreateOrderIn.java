package ru.hse.message.supervisor;

import java.util.List;import java.util.concurrent.CopyOnWriteArrayList;import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;import ru.hse.message.Message;

@Slf4j
public class CreateOrderIn extends Message {

  public int minute;

  public List<Dish> dishes = new CopyOnWriteArrayList<Dish>();

  public CreateOrderIn(CopyOnWriteArrayList<Dish> dishes) {
    this.dishes=dishes;
  }
}
