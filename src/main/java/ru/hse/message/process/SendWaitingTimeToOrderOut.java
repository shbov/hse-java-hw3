package ru.hse.message.process;

import ru.hse.message.Message;

public class SendWaitingTimeToOrderOut extends Message {
    int time;
    SendWaitingTimeToOrderOut(int time){
        this.time= time;
    }
}
