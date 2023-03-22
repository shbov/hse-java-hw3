package ru.hse.agent;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString
public class Storage extends Agent {
  @Getter private List<Ingredient> activeIngredients;
  @Getter private Map<Integer, Integer> storage;

  public Storage(
      int id,
      SuperVisor supervisor,
      List<Ingredient> activeIngredients,
      Map<Integer, Integer> storage) {
    super(id, supervisor);
    this.activeIngredients = activeIngredients;
    this.storage = storage;
    // TODO «Проверяет», имеется ли в наличии на складе достаточный объем конкретного продукта
    // для резервирования под экземпляр блюда / напитка для выполнения определенного заказа.
    // Если такая проверка проходит успешно при получении приказа о резервировании
    // от управляющего агента, создает агент продукта.
  }

  @Override
  protected void proceed(Message o) throws Exception {}
}
