package ru.hse;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Cooker;
import ru.hse.agent.Operation;
import ru.hse.agent.SuperVisor;
import ru.hse.utilities.AgentUtility;

@Slf4j
public class SerializeExample {
  @SneakyThrows
  public static void main(String[] args) {
    List<Operation> operations = new CopyOnWriteArrayList<>();
    SuperVisor superVisor = new SuperVisor(AgentUtility.generateID(SuperVisor.class));

    int id = AgentUtility.generateID(Cooker.class);
    String name = "Nick";

    Cooker cooker = new Cooker(id, superVisor, name, operations, true);
    List<Cooker> all = new CopyOnWriteArrayList<>();
    all.add(cooker);
    all.add(cooker);

    String result = new ObjectMapper().writeValueAsString(all);
    System.out.println(result);

    FileInputStream fis = new FileInputStream("src/main/resources/cookers.json");
    String json = IOUtils.toString(fis, "UTF-8");

    ObjectMapper mapper = new ObjectMapper();
    List<Cooker> myObjects = Arrays.asList(mapper.readValue(json, Cooker[].class));

    for (Cooker cook : myObjects) {
      System.out.println(cook);
    }
  }
}
