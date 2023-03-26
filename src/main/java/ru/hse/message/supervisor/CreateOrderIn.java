package ru.hse.message.supervisor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;
import ru.hse.message.Message;

@Slf4j
public class CreateOrderIn extends Message {

  public int minute;

  @Getter
  public List<Dish> dishes = new CopyOnWriteArrayList<Dish>();

  @Getter
  private int idVisitor;

  public CreateOrderIn(List<Dish> dishes, int idVisitor) {
    this.dishes = dishes;
    this.idVisitor=idVisitor;
  }
}
