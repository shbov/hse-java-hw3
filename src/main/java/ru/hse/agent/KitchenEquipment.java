package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class KitchenEquipment extends Agent {
  String typeOfEquipment;

  public KitchenEquipment(int id, String typeOfEquipment, SuperVisor supervisor) {
    super(id, supervisor);
    this.typeOfEquipment = typeOfEquipment;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // а что делать сковородке, когда она просто сковородка
    return;
    }
}
