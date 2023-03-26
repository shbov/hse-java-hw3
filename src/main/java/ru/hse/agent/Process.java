package ru.hse.agent;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString(callSuper = true)
public class Process extends Agent {
  @Getter private int SummaryTime;
  @Getter private List<Operation> operations;

  public Process(int id, SuperVisor supervisor, List<Operation> operations)
      throws InterruptedException {
    super(id, supervisor);
    this.operations = operations;
    for (Operation operation : operations) {
      Cooker cooker =
          AgentRepository.findByType(Cooker.class).stream()
              .filter(p -> !p.isActive())
              .findFirst()
              .orElse(null);
      KitchenEquipment equipment =
          AgentRepository.findByType(KitchenEquipment.class).stream()
              .filter(p -> p.getType() == operation.getEquipmentId() && !p.isActive())
              .findFirst()
              .orElse(null);
      if (cooker != null && equipment != null) {
        cooker.setActive(true);
        equipment.setActive(true);
        log.info("Cooker start cooking" + cooker);
        log.info("Reservate equipment for cooker" + equipment);
        operation.setEquipment(equipment);
        operation.setCooker(cooker);
        Date currentDate = new Date();
        operation.setStartDate(currentDate);
        operation.processing();
      } else {
        log.warn("NO available cookers");
        Thread.sleep(100);
      }
    }
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO Эти агенты фактически выполняют подготовку заказа.
    //  Они могут обращаться к агентам, представляющим человека (повара, готовящего мясо)
    //  или устройство (кофеварку, которая готовит эспрессо).
    //  Их цель – обеспечить выполнение заказов, созданных агентами
    //  посетителей с помощью управляющего агента (супервизора),
    //  чтобы фактически приготовить блюда / напитки с использованием продуктов,
    //  которые представляют соответствующие агенты продуктов.

  }
  // список операций
}
