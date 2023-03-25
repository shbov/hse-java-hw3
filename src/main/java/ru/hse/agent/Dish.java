package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

// такое чувство будто он не обменивается сообщениями
@Slf4j
@ToString(callSuper = true)
public class Dish extends Agent {
  @Getter
  @JsonProperty("dish_descr")
  private String description;

  @Getter
  @JsonProperty("operations")
  private List<Operation> operations;

  public Dish() {}

  public Dish(int id, SuperVisor supervisor, List<Operation> operations) {
    super(id, supervisor);
    this.operations = operations;
  }

  public int getCost() {
    return 0; // todo compute cost price
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO с бедолагой никто не общается
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
