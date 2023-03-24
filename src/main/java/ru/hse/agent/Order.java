package ru.hse.agent;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.order.GetWaitingTimeIn;
import ru.hse.message.order.SendWaitingTImeOut;

@Slf4j
@ToString
public class Order extends Agent {
    @Getter
    private List<Dish> dishes;

    public Order(int id, SuperVisor supervisor, List<Dish> dishes) {
        super(id, supervisor);
        this.dishes = dishes;
    }

    @Override
    protected void proceed(Message message) throws Exception {
        //TODO он еще всякие продукты резервирует и все такое, в следующий раз
        if (message instanceof GetWaitingTimeIn getWaitingTimeIn) {
            int minute = 1000;
            for (Dish dish : dishes) {
                if (dish.getId() == getWaitingTimeIn.dishID) {
                    for (Operation oper : dish.getProcess().getOperations()) {
                        minute += oper.getTime();
                    }
                }
            }

            log.info("Примерное время готовки заказа " + minute + " мин.");
            Message respond = new SendWaitingTImeOut(minute);
            //TODO отправить ответ
        }else {
          System.out.println("Message not acceptable " + message.getClass().toString());
        }

    }
}
