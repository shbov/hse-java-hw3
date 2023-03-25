package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString(callSuper = true)
public class Product extends Agent {

  @Getter
  @JsonProperty("prod_item_type")
  private int type;

  @Getter
  @JsonProperty("prod_item_company")
  private String company;

  @Getter
  @JsonProperty("prod_item_unit")
  private String unit;

  @Getter @Setter
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

  public Product() {}

  public Product(
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
    setName("name");
    this.unit = unit;
    this.quantity = quantity;
    this.delivered = delivered;
    this.validUntil = validUntil;
  }

  @Override
  protected void proceed(Message message) throws Exception {
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
