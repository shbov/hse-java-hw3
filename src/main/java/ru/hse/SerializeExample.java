package ru.hse;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Ingredient;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
public class SerializeExample {
  public static void main(String[] args) {
    try {
      FileInputStream fis = new FileInputStream("src/main/resources/products.json");
      String json = IOUtils.toString(fis, StandardCharsets.UTF_8);

      List<Ingredient> myObjects = DeserializeUtility.deserializeListOfObjects(json, "products", new TypeReference<>() {});
      myObjects.forEach(System.out::println);
    }  catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
