package ru.hse.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
/** Утилита, позволяющая быстро десериализовать список объектов одного типа */
public class DeserializeUtility {
  /**
   * Утилита, позволяющая быстро десериализовать список объектов одного типа
   *
   * @param json Json-текст
   * @param keyword Ключ в json
   * @param type Класс для десериализации
   * @return Вектор объектов типа MyClass
   * @param <T> List<MyClass>
   */
  public static <T> T deserializeListOfObjects(
      String json, String keyword, final TypeReference<T> type) {
    T list = null;

    try {
      ObjectMapper mapper = new ObjectMapper();
      String text = mapper.readTree(json).findPath(keyword).toString();
      list = mapper.readValue(text, type);
    } catch (JsonProcessingException exception) {
      log.error(exception.getMessage());
    }

    return list;
  }

  public static <T> T deserializeObject(String json, final TypeReference<T> type) {
    T list = null;

    try {
      ObjectMapper mapper = new ObjectMapper();
      list = mapper.readValue(json, type);
    } catch (JsonProcessingException exception) {
      log.error(exception.getMessage());
    }

    return list;
  }
}
