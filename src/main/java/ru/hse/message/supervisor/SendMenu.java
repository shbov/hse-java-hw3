package ru.hse.message.supervisor;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;
import ru.hse.message.Message;

@Slf4j
public class SendMenu extends Message {

  public List<Dish> dishes;
}
