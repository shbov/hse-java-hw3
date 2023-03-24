package ru.hse.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class DeserializeUtility {

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
}
