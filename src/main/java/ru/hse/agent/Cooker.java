package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.cooker.ChangeOperationIn;

@Slf4j
@ToString
public class Cooker extends Agent {
  @JsonIgnore public Operation action;

  @JsonProperty("cook_name")
  @Getter
  private String name;

  @JsonProperty("cook_active")
  @Getter
  private boolean active;

  public Cooker() {}

  public Cooker(int id, SuperVisor supervisor, String name, Operation action, boolean active) {
    super(id, supervisor);
    this.name = name;
    this.action = action;
    this.active = active;
  }

  @Override
  @JsonProperty("cook_id")
  public int getId() {
    return super.getId();
  }

  @Override
  protected void proceed(Message message) throws Exception {
    if (message instanceof ChangeOperationIn changeOperationIn) {
      action = changeOperationIn.operation;
      log.info("Start doing operation: " + action.getName());
      Thread.sleep(100000);
      log.info("I'm done operation: " + action.getName());
    } else {
      System.out.println("Message not acceptable " + message.getClass().toString());
    }
  }
}
