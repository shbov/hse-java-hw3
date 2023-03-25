package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.cooker.ChangeOperationIn;

@Slf4j
@ToString(callSuper = true)
public class Cooker extends Agent {
  @JsonIgnore public Operation action;

  @JsonProperty("cook_active")
  @Getter @Setter
  private boolean active;

  public Cooker() {}

  public Cooker(int id, SuperVisor supervisor, String name, Operation action, boolean active) {
    super(id, supervisor);
    setName(name);
    this.action = action;
    this.active = active;
  }

  @Override
  @JsonProperty("id")
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
      log.error("Message not acceptable " + message.getClass().toString());
    }
  }

  @JsonProperty("name")
  private void unpackName(String name) {
    setName(name);
  }

  @JsonProperty("id")
  private void unpackId(int id) {
    setId(id);
  }
}
