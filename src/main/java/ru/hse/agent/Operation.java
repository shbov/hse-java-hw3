package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

import lombok.Getter;
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

  @Getter
  @JsonProperty("oper_coocker_id")
  private int cookerId;

  @Getter
  @JsonProperty("oper_started")
  private Date startDate;

  @Getter
  @JsonProperty("oper_ended")
  private Date endDate;

  @Getter
  @JsonProperty("oper_active")
  private Boolean active;

  @Getter
  private KitchenEquipment equipment;

  @Getter
  @JsonProperty("products")
  private List<Ingredient> products;

  public Operation() {}

  public Operation(
      int id, SuperVisor supervisor, int cook, KitchenEquipment equipment) {
    super(id, supervisor);
    this.cookerId = cook;
    this.equipment = equipment;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    //TODO либо так, либо при конструкторе сразу назначать
    Message requestCooker = new ReservateCookerIn(this.getId());
    Message requestEquipment = new ReservateEquipmentIn(this.getId());

  }
  // продукты для операции

  @JsonProperty("id")
  private void unpackId(int id) {
    setId(id);
  }
}
