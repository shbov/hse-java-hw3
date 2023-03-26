package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuOut;
import ru.hse.message.supervisor.SendTimeOut;
import ru.hse.message.visitor.RequestTimeOut;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class Visitor extends Agent {
    public Visitor() {
    }

    public Visitor(int id, SuperVisor supervisor) {
        super(id, supervisor);
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof SendMenuOut sendMenuOut) {
            Random random = new Random();
            List<String> choiceName = new CopyOnWriteArrayList<>();
            List<Dish> choiceDish = new CopyOnWriteArrayList<>();
            for (int i=0; i<= random.nextInt(sendMenuOut.dishes.size() - 1); ++i){
                choiceName.add(sendMenuOut.dishes.get(i).getName());
                choiceDish.add(sendMenuOut.dishes.get(i));
            }
            log.info("Order of " + getName() + " " + choiceName.toString());

            Message respond = new CreateOrderIn(choiceDish, getId());
            Agent superVis = AgentRepository.findByTypeAndId(SuperVisor.class, sendMenuOut.idSupervisor);
            superVis.registerMessage(respond);

            Message time = new RequestTimeOut(getId());
            superVis.registerMessage(time);
            Random rand = new Random();
            Thread.sleep(rand.nextInt(10) + 10);
            Message time2 = new RequestTimeOut(getId());
            superVis.registerMessage(time2);

        } else if (message instanceof SendTimeOut sendTimeOut) {
            log.info("Thanks for order time, Visitor#" + getId());
        } else {
            log.error("Message not acceptable " + message.getClass().toString());
        }
    }

    @JsonProperty("name")
    private void unpackName(String name) {
        setName(name);
    }

    @JsonProperty("id")
    private void unpackId(int id) {
        setId(id);
    }
}
