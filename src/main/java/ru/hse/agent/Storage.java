package ru.hse.agent;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.storage.CheckIngredientIn;
import ru.hse.message.storage.FeedBackCheckIngredientOut;
import ru.hse.message.storage.ReservedIgredientForDish;

@Slf4j
@ToString
public class Storage extends Agent {
    @Getter
    private List<Ingredient> activeIngredients;
    @Getter
    private Map<Integer, Integer> storage;

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
    protected void proceed(Message message) throws Exception {
        if (message instanceof CheckIngredientIn checkIngredientIn) {
            if (storage.containsKey(checkIngredientIn.IngId)) {
                if (storage.get(checkIngredientIn.IngId) > checkIngredientIn.amount) {
                    Message respond = new FeedBackCheckIngredientOut(true);
                } else {
                    Message respond = new FeedBackCheckIngredientOut(false);
                }
            }
            Message respond = new FeedBackCheckIngredientOut(false);
      // TODO отправить ответ
    } else if (message instanceof ReservedIgredientForDish reservedIgredientForDish) {
      Ingredient product = null;
      //                    new Ingredient(AgentUtility.generateID(Ingredient.class),
      // reservedIgredientForDish.name, reservedIgredientForDish.amount, this.getSupervisor());
      Ingredient.start(product);
      storage.put(
          reservedIgredientForDish.IngId,
          storage.get(reservedIgredientForDish.IngId) - reservedIgredientForDish.amount);
    } else {
            System.out.println("Message not acceptable " + message.getClass().toString());
        }
    }
}
