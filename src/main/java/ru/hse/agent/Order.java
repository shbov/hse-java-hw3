package ru.hse.agent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.storage.ReservedIgredientForDish;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.visitor.RequestTimeOut;
import ru.hse.utilities.AgentUtility;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@ToString(callSuper = true)
public class Order extends Agent {
    @Getter
    private List<Dish> dishes;
    @Getter
    @Setter
    private List<Process> processes = new CopyOnWriteArrayList<>();

    @Getter
    private int idVisitor;

    public Order(int id, int idVisitor, SuperVisor supervisor, List<Dish> dishes) {
        super(id, supervisor);
        this.dishes = dishes;
        this.idVisitor = idVisitor;
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof CreateOrderIn createOrderIn) {
            for (Dish dish : createOrderIn.dishes) {
                log.info("Reservate product");
                for (Operation oper : dish.getOperations()) {
                    for (Ingredient ing : oper.getProducts()) {
                        Message request = new ReservedIgredientForDish(ing.getId(), ing.getQuantity());
                        getSupervisor().getStorage().registerMessage(request);
                    }
                }
                Process cooking =
                        new Process(
                                AgentUtility.generateID(Order.class), this.getSupervisor(), dish.getOperations());
                Process.start(cooking);
                log.info("Order: Process start" + cooking);
                processes.add(cooking);
            }
        } else if (message instanceof RequestTimeOut requestTimeOut) {
            for (Process process : processes) {
                process.registerMessage(requestTimeOut);
            }
        } else {
            log.error("Message not acceptable " + message.getClass().toString());
        }
    }
}
