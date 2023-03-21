package ru.hse.message.order;

import ru.hse.message.Message;

public class SendWaitingTImeOut extends Message {
    int time;

    SendWaitingTImeOut(int time) {
        this.time = time;
    }
}
