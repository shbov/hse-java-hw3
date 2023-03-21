package ru.hse.message.storage;

import ru.hse.message.Message;

public class ReservedIgredientForDish extends Message {
    int IngId;
    int amount;

    ReservedIgredientForDish(int IngId, int amount) {
        this.IngId = IngId;
        this.amount = amount;
    }
}
