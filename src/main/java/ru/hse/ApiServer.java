package ru.hse;

import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
public class ApiServer {
  public static void start() {
    get(
        "/api/logs",
        (req, res) -> {
          String json = "";

          try {
            FileInputStream fis = new FileInputStream("storage/logs/log.json");
            json = IOUtils.toString(fis, StandardCharsets.UTF_8);
          } catch (IOException exception) {
            log.error(exception.toString());
          }
          res.type("application/json\n");
          res.status(200);
          return json;
        });
  }
}
