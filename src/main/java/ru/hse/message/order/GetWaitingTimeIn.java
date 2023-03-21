package ru.hse.message.order;

import ru.hse.message.Message;

public class GetWaitingTimeIn extends Message {
    int orderID;

    GetWaitingTimeIn(int orderID) {
        this.orderID = orderID;
    }
}
