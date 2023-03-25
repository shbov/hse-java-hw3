package ru.hse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public class ProductType {
  @Getter
  @JsonProperty("prod_type_id")
  private int id;

  @Getter
  @JsonProperty("prod_type_name")
  private String name;

  @Getter
  @JsonProperty("prod_is_food")
  private boolean isFood;

  ProductType() {}
}
