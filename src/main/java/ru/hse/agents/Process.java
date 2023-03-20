package ru.hse.agents;

import java.util.List;

public class Process extends Agent {
    public int SummaryTime;
    public List<Operation> operations;
    public Process(int id, SuperVisor supervisor, List<Operation> operations) {
        super(id, supervisor);
        this.operations=operations;
    }

    @Override
    protected void proceed(Object o) throws Exception {
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
