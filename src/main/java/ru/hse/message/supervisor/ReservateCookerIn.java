package ru.hse.message.supervisor;

import ru.hse.message.Message;

public class ReservateCookerIn extends Message {
    public int operationId;
    public ReservateCookerIn(int operationId){
        this.operationId=operationId;
    }
}
