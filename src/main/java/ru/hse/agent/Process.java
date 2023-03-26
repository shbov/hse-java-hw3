package ru.hse.agent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;
import ru.hse.message.supervisor.SendTimeOut;
import ru.hse.message.visitor.RequestTimeOut;

import java.util.Date;
import java.util.List;

import static java.lang.Math.max;

@Slf4j
@ToString(callSuper = true)
public class Process extends Agent {
    @Getter
    private int summaryTime = 0;
    @Getter
    private List<Operation> operations;


    private Date startDate;

    @Getter
    @Setter
    private long margin = 0;

    public Process(int id, SuperVisor supervisor, List<Operation> operations)
            throws InterruptedException {
        super(id, supervisor);
        this.operations = operations;
        Date currentDate = new Date();
        startDate = currentDate;
        for (Operation operation : operations) {
            summaryTime += operation.getDuration();
            Cooker cooker =
                    AgentRepository.findByType(Cooker.class).stream()
                            .filter(p -> !p.isActive())
                            .findFirst()
                            .orElse(null);
            KitchenEquipment equipment =
                    AgentRepository.findByType(KitchenEquipment.class).stream()
                            .filter(p -> p.getType() == operation.getEquipmentId() && !p.isActive())
                            .findFirst()
                            .orElse(null);
            if (cooker != null && equipment != null) {
                cooker.setActive(true);
                equipment.setActive(true);
                log.info("Cooker start cooking" + cooker);
                log.info("Reservate equipment for cooker" + equipment);
                operation.setEquipment(equipment);
                operation.setCooker(cooker);
                currentDate = new Date();
                operation.setStartDate(currentDate);
                operation.processing();
            } else {
                log.warn("NO available cookers");
                Thread.sleep(100);
            }
        }
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof RequestTimeOut requestTimeOut) {
            Date currectDate = new Date();
            if (margin == 0) {
                margin = currectDate.getTime() - startDate.getTime();
            }
            long currentDistance = summaryTime - (currectDate.getTime() - startDate.getTime() - margin);
            log.info("Approximate dish preparation time, process#" + this.getId() + ", " + max(currentDistance, 0) + " min.");
            Message respond = new SendTimeOut(currentDistance);
            Visitor visitor =
                    (Visitor) AgentRepository.findByTypeAndId(Visitor.class, requestTimeOut.getIdVisitor());
            visitor.registerMessage(respond);
        } else {
            log.error("Message not acceptable " + message.getClass().toString());
        }
    }
}
