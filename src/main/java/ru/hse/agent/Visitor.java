package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuOut;

@Slf4j
public class Visitor extends Agent {
  public Visitor() {}

  public Visitor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message message) throws Exception {
    if (message instanceof SendMenuOut sendMenuOut) {
      Random random = new Random();
      Dish dish = sendMenuOut.dishes.get(random.nextInt(sendMenuOut.dishes.size()));
      log.info("Order of " + getName() + " " + dish);
      List<Dish> arr = new ArrayList<>();
      arr.add(dish);

      Message respond = new CreateOrderIn(arr);
      Agent superVis = AgentRepository.findByTypeAndId(SuperVisor.class, sendMenuOut.idSupervisor);
      superVis.registerMessage(respond);
    } else {
      log.error("Message not acceptable " + message.getClass().toString());
    }
  }

  @JsonProperty("name")
  private void unpackName(String name) {
    setName(name);
  }

  @JsonProperty("id")
  private void unpackId(int id) {
    setId(id);
  }
}
