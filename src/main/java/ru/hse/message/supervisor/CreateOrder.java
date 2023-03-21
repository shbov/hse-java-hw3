package ru.hse.message.supervisor;

import java.util.List;import java.util.concurrent.CopyOnWriteArrayList;import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;import ru.hse.message.Message;

@Slf4j
public class CreateOrder extends Message {

  public int minute;

  private List<Dish> dishes = new CopyOnWriteArrayList<Dish>();

  public CreateOrder() {
    //    Варианты возможного поведения агента:
    //    1. Добавить выбранный элемент меню (блюдо или напиток) в заказ.
    //    2. Удалить выбранный элемент меню (блюдо или напиток) из заказа.
    //    3. Отключить (сделать неактивным) пункт меню из-за недоступности необходимого ресурса.
    //    4. Включить (сделать активным) ранее отключенный пункт меню из-за появления необходимого
    // ресурса.
    //    5. «Попросить» управляющего агента создать экземпляр агента заказа.
    //    6. Отменить заказ, при этом уничтожается соответствующий агент заказа.
    //    7. Получить обновленную информацию о предполагаемом времени ожидания заказа от агента
    // заказа.

  }
}
