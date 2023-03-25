package ru.hse.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuOut;
import ru.hse.message.visitor.RequestMenuOut;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

@Slf4j
public class SuperVisor extends Agent {
  @Getter private Storage storage;
  @Getter private List<KitchenEquipment> myEquipments;
  @Getter private List<Cooker> myCookers;
  @Getter private List<Operation> myOperations;
  @Getter private List<Dish> myDishes;

  // TODO concurrent lists
  public SuperVisor(int id) {
    super(id, null);

    try {
      storage = new Storage(AgentUtility.generateID(Storage.class), this);
      storage.initStorage();

      initCookers();
      initEquipment();
      initOperations();
      initDishes();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  private void initDishes() throws IOException {
    FileInputStream fisDish = new FileInputStream("src/main/resources/dishes.json");
    String jsonDish = IOUtils.toString(fisDish, StandardCharsets.UTF_8);
    myDishes =
        DeserializeUtility.deserializeListOfObjects(jsonDish, "dishes", new TypeReference<>() {});
    myDishes.forEach(System.out::println);
  }

  private void initOperations() throws IOException {
    FileInputStream fisOp = new FileInputStream("src/main/resources/operation_log.json");
    String jsonOp = IOUtils.toString(fisOp, StandardCharsets.UTF_8);
    myOperations =
        DeserializeUtility.deserializeListOfObjects(jsonOp, "operations", new TypeReference<>() {});
    myOperations.forEach(System.out::println);
  }

  private void initEquipment() throws IOException {
    FileInputStream fisEq = new FileInputStream("src/main/resources/equipment.json");
    String jsonEq = IOUtils.toString(fisEq, StandardCharsets.UTF_8);
    myEquipments =
        DeserializeUtility.deserializeListOfObjects(jsonEq, "equipment", new TypeReference<>() {});
    myEquipments.forEach(System.out::println);
  }

  private void initCookers() throws IOException {
    FileInputStream fisCook = new FileInputStream("src/main/resources/cookers.json");
    String jsonCook = IOUtils.toString(fisCook, StandardCharsets.UTF_8);

    myCookers =
        DeserializeUtility.deserializeListOfObjects(jsonCook, "cookers", new TypeReference<>() {});
    myCookers.forEach(System.out::println);
  }

  @Override
  protected void proceed(Message message) throws Exception {
    if (message instanceof CreateOrderIn createOrderIn) {
      Order order =
          new Order(
              AgentUtility.generateID(Order.class), this.getSupervisor(), createOrderIn.dishes);
      Order.start(order);
      order.registerMessage(createOrderIn);
    } else if (message instanceof RequestMenuOut requestMenuOut) {
      log.info("This is our menu: " + myDishes.toString());
      Message respond = new SendMenuOut(myDishes, getId());
      Visitor visitor =
          (Visitor) AgentRepository.findByTypeAndId(Visitor.class, requestMenuOut.idVisitor);
      visitor.registerMessage(respond);
    } else {
      log.error("Message not acceptable " + message.getClass().toString());
    }
  }
}
