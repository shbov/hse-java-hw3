package ru.hse.agents;

import ru.hse.Message.Message;
import ru.hse.Message.MessageForSuperVisor.CreateOrder;

public class KitchenEquipment extends Agent{
    String typeOfEquipment;
    public KitchenEquipment(int id, String typeOfEquipment, SuperVisor supervisor) {
        super(id, supervisor);
        this.typeOfEquipment=typeOfEquipment;
    }

    @Override
    protected void proceed(Object o) throws Exception {
        //а что делать сковородке, когда она просто сковородка
        return;
    }
}
