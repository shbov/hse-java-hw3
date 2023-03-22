package ru.hse.message.order;

import ru.hse.message.Message;

public class GetWaitingTimeIn extends Message {
    public int dishID;

    public GetWaitingTimeIn(int dishID) {
        this.dishID = dishID;
    }
}
