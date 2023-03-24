package ru.hse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class EquipmentType {
  @JsonProperty("equip_type_id")
  @Getter
  private int id;

  @JsonProperty("equip_type_name")
  @Getter
  private String name;

  EquipmentType() {}

  EquipmentType(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
