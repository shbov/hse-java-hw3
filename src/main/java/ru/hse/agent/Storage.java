package ru.hse.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.message.Message;
import ru.hse.message.storage.CheckIngredientIn;
import ru.hse.message.storage.FeedBackCheckIngredientOut;
import ru.hse.message.storage.ReservedIgredientForDish;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
@ToString(callSuper = true)
public class Storage extends Agent {
  @Getter private List<Product> products = new CopyOnWriteArrayList<>();

  public Storage(int id, SuperVisor supervisor) {
    super(id, supervisor);
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
      new Ingredient(
          AgentUtility.generateID(Ingredient.class),
          reservedIgredientForDish.name,
          reservedIgredientForDish.amount,
          this.getSupervisor());
      Ingredient.start(product);
      storage.put(
          reservedIgredientForDish.IngId,
          storage.get(reservedIgredientForDish.IngId) - reservedIgredientForDish.amount);
    } else {
      log.error("Message not acceptable " + message.getClass().toString());
    }
  }

  public void initStorage() {
    try {
      FileInputStream fis = new FileInputStream("src/main/resources/products.json");
      String json = IOUtils.toString(fis, StandardCharsets.UTF_8);

      products =
          DeserializeUtility.deserializeListOfObjects(json, "products", new TypeReference<>() {});
      products.forEach(System.out::println);
    } catch (IOException ex) {
      log.error(ex.getMessage());
    }
  }
}
