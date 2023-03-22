package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.cooker.ChangeOperationIn;
import ru.hse.message.storage.CheckIngredientIn;
import ru.hse.message.storage.FeedBackCheckIngredientOut;
import ru.hse.message.storage.ReservedIgredientForDish;
import ru.hse.utilities.AgentUtility;

@Slf4j
@ToString
public class Cooker extends Agent {
  @JsonProperty("cook_name")
  @Getter
  private String name;

  @JsonProperty("cook_active")
  @Getter
  private boolean active;

  @Override
  @JsonProperty("cook_id")
  public int getId() {
    return super.getId();
  }

  @JsonIgnore public Operation action;

  /*
  Публичный конструктор; нужен для (де)-сериализации
   */
  public Cooker() {}

  public Cooker(
      int id, SuperVisor supervisor, String name, Operation action, boolean active) {
    super(id, supervisor);
    this.name = name;
    this.action = action;
    this.active = active;
  }

  @Override
  protected void proceed(Message message) throws Exception {
    log.info(Thread.currentThread().getName());
    if (message instanceof ChangeOperationIn changeOperationIn) {
       action= changeOperationIn.operation;
       log.info("Start doing operation: "+action.getName());
       Thread.sleep(action.getTime()*100);
       log.info("I'm done operation: "+action.getName());
    } else {
      System.out.println("Message not acceptable " + message.getClass().toString());
    }
  }
}
