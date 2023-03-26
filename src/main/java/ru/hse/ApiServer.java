package ru.hse;

import static spark.Spark.*;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Visitor;
import ru.hse.message.Message;
import ru.hse.message.visitor.RequestMenuOut;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
@UtilityClass
public class ApiServer {
  public static void start() {
    path(
        "/api",
        () -> {
          post(
              "/visitor-order",
              "application/json",
              (req, res) -> {
                Visitor visitor = null;

                try {
                  visitor =
                      DeserializeUtility.deserializeObject(
                          req.queryParams("visitor"), new TypeReference<>() {});
                } catch (Exception e) {
                  log.error(e.getMessage());
                }

                if (visitor != null) {
                  log.info("Пришел пользователь из API: {}", visitor);
                  Visitor.start(visitor);

                  Message mess = new RequestMenuOut(visitor.getId());
                  Main.getSuperVisor().registerMessage(mess);

                  Thread.sleep(100);
                  return "заказ получен и находится в работе";
                }

                return "error: request is invalid";
              });
          get(
              "/logs",
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
