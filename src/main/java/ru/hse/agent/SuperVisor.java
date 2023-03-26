package ru.hse.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.message.Message;
import ru.hse.message.storage.RefreshMenu;
import ru.hse.message.supervisor.CreateOrderIn;
import ru.hse.message.supervisor.SendMenuOut;
import ru.hse.message.visitor.RequestMenuOut;
import ru.hse.message.visitor.RequestTimeOut;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SuperVisor extends Agent {
    @Getter
    private Storage storage;
    @Getter
    private List<KitchenEquipment> myEquipments;
    @Getter
    private List<Cooker> myCookers;
    @Getter
    private List<Operation> myOperations;
    @Getter
    private List<Dish> myDishes;

    public SuperVisor(int id) {
        super(id, null);

        try {
            storage = new Storage(AgentUtility.generateID(Storage.class), this);
            storage.initStorage();
            initCookers();
            initEquipment();
            initDishes();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void initDishes() throws IOException {
        FileInputStream fisDish = new FileInputStream("src/main/resources/dishes.json");
        String jsonDish = IOUtils.toString(fisDish, StandardCharsets.UTF_8);
        myDishes =
                DeserializeUtility.deserializeListOfObjects(jsonDish, "dishes", new TypeReference<>() {
                });

        if (myDishes == null) {
            myDishes = new ArrayList<>();
            log.warn("список блюд пуст!");
        }

        myDishes.forEach(System.out::println);
    }

    private void initEquipment() throws IOException {
        FileInputStream fisEq = new FileInputStream("src/main/resources/equipment.json");
        String jsonEq = IOUtils.toString(fisEq, StandardCharsets.UTF_8);
        myEquipments =
                DeserializeUtility.deserializeListOfObjects(jsonEq, "equipment", new TypeReference<>() {
                });

        if (myEquipments == null) {
            myEquipments = new ArrayList<>();
            log.warn("список оборудования пуст!");
        }

        myEquipments.forEach(
                c -> {
                    System.out.println(c);
                    KitchenEquipment.start(c);
                });
    }

    private void initCookers() throws IOException {
        FileInputStream fisCook = new FileInputStream("src/main/resources/cookers.json");
        String jsonCook = IOUtils.toString(fisCook, StandardCharsets.UTF_8);

        myCookers =
                DeserializeUtility.deserializeListOfObjects(jsonCook, "cookers", new TypeReference<>() {
                });

        if (myCookers == null) {
            myCookers = new ArrayList<>();
            log.warn("список поваров пуст!");
        }

        myCookers.forEach(
                c -> {
                    System.out.println(c);
                    Cooker.start(c);
                });
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof CreateOrderIn createOrderIn) {
            Order order = new Order(AgentUtility.generateID(Order.class), createOrderIn.getIdVisitor(), this, createOrderIn.getDishes());
            Order.start(order);
            order.registerMessage(createOrderIn);
        } else if (message instanceof RequestMenuOut requestMenuOut) {
            Message request = new RefreshMenu(myDishes, requestMenuOut.idVisitor);
            getStorage().registerMessage(request);
            log.info("Storage>> refreshing menu ");
        } else if (message instanceof RefreshMenu refreshMenu) {
            log.info("This is our menu: " + refreshMenu.getDishes().toString());
            Message respond = new SendMenuOut(refreshMenu.getDishes(), getId());
            Visitor visitor =
                    (Visitor) AgentRepository.findByTypeAndId(Visitor.class, refreshMenu.getIdVisitor());
            visitor.registerMessage(respond);
        } else if (message instanceof RequestTimeOut requestTimeOut) {
            log.info("Dear guest " + requestTimeOut.getId() + ", now I'll find out the waiting time");
            Order ourOrder = (Order) AgentRepository.getAGENTS().stream()
                    .filter(agent -> agent.getClass() == Order.class && ((Order) agent).getIdVisitor() == requestTimeOut.getIdVisitor())
                    .findFirst()
                    .orElse(null);
            ourOrder.registerMessage(message);
        } else {
            log.error("Message not acceptable " + message.getClass().toString());
        }
    }
}
