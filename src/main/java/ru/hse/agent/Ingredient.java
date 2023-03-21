package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;

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
  protected void proceed(Object o) throws Exception {
    // TODO какие команды приходят продукту
  }
}
