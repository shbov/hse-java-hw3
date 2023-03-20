package ru.hse.agents;

import java.util.List;

public class Order extends Agent {
    public List<Dish> dishes;
    public Order(int id, SuperVisor supervisor, List<Dish> dishes) {
        super(id, supervisor);
        this.dishes=dishes;
    }

    @Override
    protected void proceed(Object o) throws Exception {
        // TODO Эти агенты взаимодействуют с другими типами агентов.
        //  Они отправляют агентам посетителей приблизительное время ожидания заказов;
        //  взаимодействуют с управляющим агентом и с агентами продуктов и процессов,
        //  чтобы получить необходимые ресурсы и выполнить требуемые процессы для выполнения заказа.
    }
// запускаем процессы
}
