package ru.hse;

import static spark.Spark.*;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.AgentRepository;
import ru.hse.agent.Visitor;
import ru.hse.message.Message;
import ru.hse.message.visitor.RequestMenuOut;
import ru.hse.utilities.DeserializeUtility;
import spark.Request;
import spark.Response;

@Slf4j
@UtilityClass
public class ApiServer {
  public static void start() {
    path(
        "/api",
        () -> {
          post("/visitor-order", "application/json", ApiServer::VisitorOrderMethod);
          get("/logs", ApiServer::getLogsMethod);
        });
  }

  private static String getLogsMethod(Request req, Response res) {
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

    res.type("application/json");
    res.status(200);
    return result;
  }

  private static String VisitorOrderMethod(Request req, Response res) {
    res.type("application/json");
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
      if (AgentRepository.isAgentExist(Visitor.class, visitor.getId())) {
        res.status(400);
        return "{\"message\": \"visitor with id="
            + visitor.getId()
            + " already exists!\", \"status\": \"error\"}";
      }

      Visitor.start(visitor);
      Message mess = new RequestMenuOut(visitor.getId());
      Main.getSuperVisor().registerMessage(mess);
      res.status(200);

      return "{\"message\": \"Спасибо за заказ! Он уже в работе\", \"status\": \"success\"}";
    }

    res.status(400);
    return "{\"message\": \"request is invalid\", \"status\": \"error\"}";
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
