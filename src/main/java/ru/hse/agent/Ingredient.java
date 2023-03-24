package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString
public class Ingredient extends Agent {
  @Getter
  @JsonProperty("prod_item_id")
  private int id;

  @Getter
  @JsonProperty("prod_item_type")
  private int type;

  @Getter
  @JsonProperty("prod_item_company")
  private String company;

  @Getter
  @JsonProperty("prod_item_unit")
  private String unit;

  @Getter
  @JsonProperty("prod_item_name")
  private String name;

  @Getter
  @JsonProperty("prod_item_quantity")
  private double quantity;

  @Getter
  @JsonProperty("prod_item_cost")
  private double cost;

  @Getter
  @JsonProperty("prod_item_delivered")
  private Date delivered;

  @Getter
  @JsonProperty("prod_item_valid_until")
  private Date validUntil;

  public Ingredient() {}

  public Ingredient(
      int id,
      SuperVisor supervisor,
      int type,
      String company,
      String unit,
      double quantity,
      Date delivered,
      Date validUntil) {
    super(id, supervisor);
    this.type = type;
    this.company = company;
    this.name = name;
    this.unit = unit;
    this.quantity = quantity;
    this.delivered = delivered;
    this.validUntil = validUntil;
  }

  @Override
  protected void proceed(Message message) throws Exception {
    // TODO с бедолагой никто не общается
  }
}
