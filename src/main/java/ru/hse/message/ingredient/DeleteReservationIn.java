package ru.hse.message.ingredient;

import ru.hse.message.Message;

public class DeleteReservationIn extends Message {
    int IngId;
    int amount;

    DeleteReservationIn(int IngId, int amount) {
        this.IngId = IngId;
        this.amount = amount;
    }
}
