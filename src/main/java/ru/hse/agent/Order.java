package ru.hse.agent;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;

@Slf4j
@ToString
public class Order extends Agent {
  @Getter private List<Dish> dishes;

  public Order(int id, SuperVisor supervisor, List<Dish> dishes) {
    super(id, supervisor);
    this.dishes = dishes;
  }

  @Override
  protected void proceed(Message message) throws Exception {
    log.info(Thread.currentThread().getName());

    if (message instanceof CreateOrderIn createOrderIn) {
      log.info("Примерное время готовки заказа " + createOrderIn.minute + " мин.");
    }

    // Типовое поведение агента:
    // 1. Принимает и обрабатывает сообщения от управляющего агента.
    // 2. Отправляет агенту посетителя информацию о времени ожидания его заказа.
    // 3. «Просит» агентов процесса предоставить оценку времени ожидания.
    // 4. Обрабатывает ответ от агентов процессов о времени ожидания готовности блюд / напитков.
    // 5. Резервирует необходимые ресурсы для выполнения заказа.
    // 6. Отменяет резервирование определенного ресурса (при отмене заказа).
    // 7. Обрабатывает ответ от агентов продуктов о результате резервирования.
    // 8. Продолжает процесс выполнения заказа; заказ может не быть обработанным после первого
    // завершения такого поведения из-за информации об очереди, предоставленной управляющим агентом
    // (опционально).
  }
}
