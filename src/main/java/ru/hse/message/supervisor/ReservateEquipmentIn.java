package ru.hse.message.supervisor;

import ru.hse.message.Message;

public class ReservateEquipmentIn extends Message {
    public int operationId;
    public ReservateEquipmentIn(int operationId){
        this.operationId=operationId;
    }
}
