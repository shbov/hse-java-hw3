package ru.hse.agent;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.ReservateCookerIn;
import ru.hse.message.supervisor.ReservateEquipmentIn;

@Slf4j
@ToString
public class Operation extends Agent {
  @Getter private Cooker cook;
  @Getter private int time;
  @Getter private KitchenEquipment equipment;

  public Operation(
      int id, SuperVisor supervisor, Cooker cook, KitchenEquipment equipment, int time) {
    super(id, supervisor);
    this.cook = cook;
    this.equipment = equipment;
    this.time = time;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    //TODO либо так, либо при конструкторе сразу назначать
    Message requestCooker = new ReservateCookerIn(this.getId());
    Message requestEquipment = new ReservateEquipmentIn(this.getId());

  }
  // продукты для операции
}
