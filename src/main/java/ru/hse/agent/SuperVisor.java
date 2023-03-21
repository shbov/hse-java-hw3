package ru.hse.agent;

import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class SuperVisor extends Agent {

  public SuperVisor(int id, SuperVisor supervisor) {
    super(id, supervisor);
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO он тут главная шишка всеми помыкает
  }
}
