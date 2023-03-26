package ru.hse;

import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
@UtilityClass
public class ApiServer {
  public static void start() {
    get(
        "/api/logs",
        (req, res) -> {
          String result = "{}";

          try {
            FileInputStream fis = new FileInputStream("storage/logs/log.json");
            List<String> json = IOUtils.readLines(fis);

            if (!json.isEmpty()) {
              result = "[" + joinLinesWithComma(json) + "]";
            } else {
              result = json.toString();
            }
          } catch (IOException exception) {
            log.error(exception.toString());
          }

          res.type("application/json\n");
          res.status(200);
          return result;
        });
  }

  private static String joinLinesWithComma(List<String> lines) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < lines.size(); i++) {
      sb.append(lines.get(i));
      if (i != lines.size() - 1) {
        sb.append(",");
      }
    }

    return sb.toString();
  }
}
