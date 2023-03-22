package ru.hse.message.order;

import ru.hse.message.Message;

public class SendWaitingTImeOut extends Message {
    public int time;

    public SendWaitingTImeOut(int time) {
        this.time = time;
    }
}
