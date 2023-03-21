package ru.hse.agent;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
public class Cooker extends Agent {
  public String name;
  public List<Operation> actions;

  public Cooker(int id, SuperVisor supervisor, String name, List<Operation> actions) {
    super(id, supervisor);
    this.name = name;
    this.actions = actions;
  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO представляет конкретного человека – повара ресторана,  управляет его работой (назначает
    // ему выполнение определенной операции,
    //  отменяет / приостанавливает (опционально) ранее назначенную ему операцию,
    //  возобновляет ранее приостановленную им операцию), принимает от него события,
    //  связанные с выполнением им операций («приступил к выполнению операции»,
    //  «выполнил операцию», «отменил выполнение операции» (испорчен продукт в процессе
    // приготовления)).
  }
}
