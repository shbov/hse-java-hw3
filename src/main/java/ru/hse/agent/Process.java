package ru.hse.agent;

import java.util.List;
import lombok.Getter;import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

@Slf4j
@ToString(callSuper = true)
public class Process extends Agent {
  @Getter private int SummaryTime;
  @Getter private List<Operation> operations;

  public Process(int id, SuperVisor supervisor, List<Operation> operations) {
    super(id, supervisor);
    this.operations = operations;

  }

  @Override
  protected void proceed(Message o) throws Exception {
    // TODO Эти агенты фактически выполняют подготовку заказа.
    //  Они могут обращаться к агентам, представляющим человека (повара, готовящего мясо)
    //  или устройство (кофеварку, которая готовит эспрессо).
    //  Их цель – обеспечить выполнение заказов, созданных агентами
    //  посетителей с помощью управляющего агента (супервизора),
    //  чтобы фактически приготовить блюда / напитки с использованием продуктов,
    //  которые представляют соответствующие агенты продуктов.

  }
    // список операций
}
