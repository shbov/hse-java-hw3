package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuOut;
import ru.hse.message.visitor.RequestMenuOut;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
public class Visitor extends Agent {
    @Getter
    @JsonProperty("vis_name")
    private String name;

    @Getter
    @JsonProperty("vis_id")
    private int id;

    public Visitor() {
    }

    public Visitor(int id, SuperVisor supervisor) {
        super(id, supervisor);
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof SendMenuOut sendMenuOut) {
            Random random = new Random();
            Dish dish = sendMenuOut.dishes.get(random.nextInt(sendMenuOut.dishes.size()));
            log.info("Order of " + getName() + " " + dish);
            Message respond = new CreateOrderIn(new ArrayList<Dish>() {{
                add(dish);
            }});

            Agent supervisitor = AgentRepository.findByTypeAndId(Visitor.class, sendMenuOut.idSupervisor);
            supervisitor.registerMessage(respond);
        } else {
            System.out.println("Message not acceptable " + message.getClass().toString());
        }
    }
}
