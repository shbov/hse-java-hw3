package ru.hse.agent;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

// такое чувство будто он не обменивается сообщениями
@Slf4j
@ToString
public class KitchenEquipment extends Agent {
  @Getter private String typeOfEquipment;

  public KitchenEquipment(int id, String typeOfEquipment, SuperVisor supervisor) {
    super(id, supervisor);
    this.typeOfEquipment = typeOfEquipment;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // а что делать сковородке, когда она просто сковородка
    //TODO с бедолагой никто не общается
    }
}
