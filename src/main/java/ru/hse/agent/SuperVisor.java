package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class SuperVisor extends Agent {

  public SuperVisor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // Запускает процесс создания нового заказа. На основании запроса от агента посетителя создает
    // агента заказа, а после выполнения заказа контролирует уничтожение агента заказа.
    // Взаимодействует с агентом склада. «Приказывает» ему зарезервировать для каждого экземпляра
    // заказанного блюда (напитка) из определенного заказа заданный объем конкретного продукта.
    // Создает и уничтожает всех прочих агентов.
  }
}
