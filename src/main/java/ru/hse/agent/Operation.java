package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.ReservateCookerIn;
import ru.hse.message.supervisor.ReservateEquipmentIn;

@Slf4j
@ToString(callSuper = true)
public class Operation extends Agent {
  @Getter
  @JsonProperty("oper_proc")
  private int processId;

  @Getter @Setter
  @JsonProperty("oper_started")
  private Date startDate;

  @Getter @Setter
  @JsonProperty("oper_ended")
  private Date endDate;

  @Getter @Setter
  @JsonProperty("oper_active")
  private Boolean active;

  @Getter
  @JsonProperty("duration")
  private double duration;

  @Getter @Setter
  private KitchenEquipment equipment;

  @Getter @Setter
  private Cooker cooker;

  @Getter @Setter
  @JsonProperty("equip_type")
  private int equipmentId;

  @Getter
  @JsonProperty("products")
  private List<Ingredient> products;

  public Operation() {}

  public Operation(
      int id, SuperVisor supervisor, KitchenEquipment equipment) {
    super(id, supervisor);
    this.equipment = equipment;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    //TODO либо так, либо при конструкторе сразу назначать
    Message requestCooker = new ReservateCookerIn(this.getId());
    Message requestEquipment = new ReservateEquipmentIn(this.getId());

  }
  protected void processing() throws InterruptedException {
    Thread.sleep((int)duration*10);
    Date currentDate = new Date();
    log.info("Process has ended "+currentDate+ this);
    cooker.setActive(false);
    equipment.setActive(false);
    this.setEndDate(currentDate);
  }

  @JsonProperty("id")
  private void unpackId(int id) {
    setId(id);
  }

  public void findAvailableEquipment() {
    // ..
    // equipment = find by id (" equipmentId ");
  }
}
