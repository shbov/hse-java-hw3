package ru.hse.message.supervisor;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class CreateOrder extends Message {

  public int minute;
}
