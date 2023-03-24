package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

// такое чувство будто он не обменивается сообщениями
@Slf4j
@ToString
public class KitchenEquipment extends Agent {
  @Getter
  @JsonProperty("equip_type")
  private int type;

  @Getter
  @JsonProperty("equip_name")
  private String name;

  @Getter
  @JsonProperty("equip_active")
  private boolean active;

  public KitchenEquipment(){}

  public KitchenEquipment(int id, SuperVisor supervisor, int type, String name, boolean active) {
    super(id, supervisor);
    this.type = type;
    this.name = name;
    this.active = active;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // а что делать сковородке, когда она просто сковородка
    // TODO с бедолагой никто не общается
  }
}
