package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

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

  @JsonIgnore public List<Operation> actions;

  /*
  Публичный конструктор; нужен для (де)-сериализации
   */
  public Cooker() {}

  public Cooker(
      int id, SuperVisor supervisor, String name, List<Operation> actions, boolean active) {
    super(id, supervisor);
    this.name = name;
    this.actions = actions;
    this.active = active;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO представляет конкретного человека – повара ресторана,  управляет его работой (назначает
    // ему выполнение определенной операции,
    //  отменяет / приостанавливает (опционально) ранее назначенную ему операцию,
    //  возобновляет ранее приостановленную им операцию), принимает от него события,
    //  связанные с выполнением им операций («приступил к выполнению операции»,
    //  «выполнил операцию», «отменил выполнение операции» (испорчен продукт в процессе
    // приготовления)).
  }
}
