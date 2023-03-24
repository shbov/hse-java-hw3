package ru.hse.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.message.Message;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
public class SuperVisor extends Agent {
  @Getter private List<Ingredient> myIngredients;
  @Getter private List<KitchenEquipment> myEquipments;
  @Getter private List<Cooker> myCookers;
  @Getter private List<Operation> myOperations;

  public SuperVisor(int id) {

    super(id, null);
    try {
      FileInputStream fis = new FileInputStream("src/main/resources/products.json");
      String json = IOUtils.toString(fis, StandardCharsets.UTF_8);
      myIngredients =
          DeserializeUtility.deserializeListOfObjects(json, "products", new TypeReference<>() {});
      myIngredients.forEach(System.out::println);

      FileInputStream fisCook = new FileInputStream("src/main/resources/cookers.json");
      String jsonCook = IOUtils.toString(fisCook, StandardCharsets.UTF_8);
      myCookers =
          DeserializeUtility.deserializeListOfObjects(
              jsonCook, "cookers", new TypeReference<>() {});
      myCookers.forEach(System.out::println);

      FileInputStream fisEq = new FileInputStream("src/main/resources/equipment.json");
      String jsonEq = IOUtils.toString(fisEq, StandardCharsets.UTF_8);
      myEquipments =
          DeserializeUtility.deserializeListOfObjects(
              jsonEq, "equipment", new TypeReference<>() {});
      myEquipments.forEach(System.out::println);

      FileInputStream fisOp = new FileInputStream("src/main/resources/operation_log.json");
      String jsonOp = IOUtils.toString(fisOp, StandardCharsets.UTF_8);
      myOperations =
          DeserializeUtility.deserializeListOfObjects(
              jsonOp, "operation_log", new TypeReference<>() {});
      myOperations.forEach(System.out::println);

    } catch (IOException e) {
      log.error(e.getMessage());
    }
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
