package ru.hse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class MenuDishes {
  @Getter
  @JsonProperty("menu_dish_id")
  private int id;

  @Getter
  @JsonProperty("menu_dish_card")
  private int cardId;

  @Getter
  @JsonProperty("menu_dish_price")
  private double price;

  @Getter
  @JsonProperty("menu_dish_active")
  private boolean isActive;

  MenuDishes() {}
}
