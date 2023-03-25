package ru.hse.agent;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.ingredient.ReservateIngredientIn;
import ru.hse.message.order.GetWaitingTimeIn;
import ru.hse.message.order.SendWaitingTImeOut;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.utilities.AgentUtility;

@Slf4j
@ToString(callSuper = true)
public class Order extends Agent {
    @Getter
    private List<Dish> dishes;

    public Order(int id, SuperVisor supervisor, List<Dish> dishes) {
        super(id, supervisor);
        this.dishes = dishes;
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof CreateOrderIn createOrderIn) {
            for(Dish dish :createOrderIn.dishes){
                for( Operation oper: dish.getOperations()){
                    for ()
                    Message request = new ReservateIngredientIn(oper.getProducts());
                }
            }
        }
        else if (message instanceof GetWaitingTimeIn getWaitingTimeIn) {
            int minute = 1000;
            for (Dish dish : dishes) {
                if (dish.getId() == getWaitingTimeIn.dishID) {
                    for (Operation oper : dish.getOperations()) {
                        //TODO тут нужно адекватно время считать
                        minute += 3;
                    }
                }
            }

            log.info("Примерное время готовки заказа " + minute + " мин.");
            Message respond = new SendWaitingTImeOut(minute);
            //TODO отправить ответ
        }else {
          log.error("Message not acceptable " + message.getClass().toString());
        }

    }
}
