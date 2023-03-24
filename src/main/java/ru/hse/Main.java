package ru.hse;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Agent;
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

  public static void main(String[] args) throws IOException {
    SuperVisor superVisor = new SuperVisor(AgentUtility.generateID(SuperVisor.class));
    Agent.start(superVisor);

    FileInputStream fis = new FileInputStream("src/main/resources/visitors.json");
    String json = IOUtils.toString(fis, StandardCharsets.UTF_8);
    List<Visitor> visitors = DeserializeUtility.deserializeListOfObjects(json, "visitors", new TypeReference<>() {});
    visitors.forEach(Visitor::start);
    visitors.forEach(System.out::println);

    Agent.stop(superVisor);
    visitors.forEach(Visitor::stop);
  }
}