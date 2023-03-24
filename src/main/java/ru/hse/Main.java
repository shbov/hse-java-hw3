package ru.hse;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.SuperVisor;
import ru.hse.agent.Visitor;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
    log.info("This is an info level log message!");
    SuperVisor superVisor = new SuperVisor(AgentUtility.generateID(SuperVisor.class));
    SuperVisor.start(superVisor);
    FileInputStream fis = new FileInputStream("src/main/resources/products.json");
    String json = IOUtils.toString(fis, StandardCharsets.UTF_8);
    List<Visitor> visitors = DeserializeUtility.deserializeListOfObjects(json, "products", new TypeReference<>() {});

  }
}