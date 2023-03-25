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

  @Getter @JsonIgnore private Process process;

  @Getter
  @JsonProperty("dish_products")
  private List<Ingredient> products;

  public Dish() {}

  public Dish(int id, SuperVisor supervisor, Process process, List<Ingredient> products) {
    super(id, supervisor);
    this.process = process;
    this.products = products;
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
