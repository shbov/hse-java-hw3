package ru.hse;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Dish;
import ru.hse.agent.Ingredient;
import ru.hse.agent.Visitor;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
public class SerializeExample {
  public static void main(String[] args) {
    try {
      // путь к нужному файлу (все json в находятся в /src/main/resources);

      FileInputStream fis = new FileInputStream("src/main/resources/dishes.json");
      String json = IOUtils.toString(fis, StandardCharsets.UTF_8);

      // выбираем нужен тип, keyword = ключ для массива объеков в json
      // (например, в cookers.json = "cookers": [...] => keyword = "cookers")

      // еще я сделал сущности без агентов, они в папочке entity, надо подумать нужны ли они
      // и как и где их использовать

      // todo : dish_cards еще не делал, можно по аналогии добавить
      List<Dish> myObjects =
          DeserializeUtility.deserializeListOfObjects(json, "dishes", new TypeReference<>() {});
      myObjects.forEach(System.out::println);
    }  catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
