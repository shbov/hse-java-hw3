package ru.hse.message.supervisor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Dish;
import ru.hse.message.Message;

@Slf4j
public class SendMenuOut extends Message {

  public List<Dish> dishes = new CopyOnWriteArrayList<>();
  public int idSupervisor;
  public SendMenuOut(List<Dish> dishes, int idSupervisor){
    this.dishes=dishes;
    this.idSupervisor=idSupervisor;
  }
}
