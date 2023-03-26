package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

import java.util.Date;
import java.util.List;

@Slf4j
@ToString(callSuper = true)
public class Operation extends Agent {
    @Getter
    @JsonProperty("oper_proc")
    private int processId;

    @Getter
    @Setter
    @JsonProperty("oper_started")
    private Date startDate;

    @Getter
    @Setter
    @JsonProperty("oper_ended")
    private Date endDate;

    @Getter
    @Setter
    @JsonProperty("oper_active")
    private Boolean active;

    @Getter
    @JsonProperty("duration")
    private int duration;

    @Getter
    @Setter
    private KitchenEquipment equipment;

    @Getter
    @Setter
    private Cooker cooker;

    @Getter
    @Setter
    @JsonProperty("equip_type")
    private int equipmentId;

    @Getter
    @JsonProperty("products")
    private List<Ingredient> products;

    public Operation() {
    }

    public Operation(int id, SuperVisor supervisor, KitchenEquipment equipment) {
        super(id, supervisor);
        this.equipment = equipment;
    }

    @Override
    protected void proceed(Message o) throws Exception {
        processing();
    }

    synchronized void processing() throws InterruptedException {
        Thread.sleep(duration * 10L);
        Date currentDate = new Date();
        log.info("Process has ended " + currentDate + " " + this.getName());
        cooker.setActive(false);
        equipment.setActive(false);
        this.setEndDate(currentDate);
        notifyAll();
    }

    @JsonProperty("id")
    private void unpackId(int id) {
        setId(id);
    }
}
