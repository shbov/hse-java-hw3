package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class Ingredient extends Agent {
  int weight;
  String name;

  public Ingredient(int id, String name, int weight, SuperVisor supervisor) {
    super(id, supervisor);
    this.weight = weight;
    this.name = name;
  }

  @Override
  protected void proceed(Message message) throws Exception {}
}
