package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class Operation extends Agent {
  public Cooker cook;
  public int time;
  public KitchenEquipment equipment;

  public Operation(
      int id, SuperVisor supervisor, Cooker cook, KitchenEquipment equipment, int time) {
    super(id, supervisor);
    this.cook = cook;
    this.equipment = equipment;
    this.time = time;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO запрашивает управляющего агента зарезервировать агента повара
    // и агента оборудования для выполнения операции процесса

  }
  // продукты для операции
}
