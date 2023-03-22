package ru.hse.agent;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

//такое чувство будто он не обменивается сообщениями
@Slf4j
public class Dish extends Agent {
  public Process process;
  public List<Ingredient> products;
  public int cost;

  public Dish(int id, SuperVisor supervisor, Process process, List<Ingredient> products, int cost) {
    super(id, supervisor);
    this.process = process;
    this.products = products;
    this.cost = cost;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // содержит списки созданных управляющим агентом агентов процесса, операций и агентов продуктов
    // для приготовления конкретного блюда / напитка из заказа посетителя. Уничтожается, когда
    // данное блюдо / напиток приготовлено, а заказ выполнен.
  }
}