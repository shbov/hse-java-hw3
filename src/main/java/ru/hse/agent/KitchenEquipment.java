package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString(callSuper = true)
public class KitchenEquipment extends Agent {
  @Getter
  @JsonProperty("equip_type")
  private int type;

  @Getter @Setter
  @JsonProperty("equip_active")
  private boolean active;

  public KitchenEquipment(){}

  public KitchenEquipment(int id, SuperVisor supervisor, int type, String name, boolean active) {
    super(id, supervisor);
    this.type = type;
    setName(name);
    this.active = active;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // а что делать сковородке, когда она просто сковородка
  }

  @JsonProperty("name")
  private void unpackName(String name) {
    setName(name);
  }
}
