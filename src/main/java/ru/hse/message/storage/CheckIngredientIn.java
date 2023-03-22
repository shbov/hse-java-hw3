package ru.hse.message.storage;

import ru.hse.message.Message;

public class CheckIngredientIn extends Message {
    public int IngId;
    public int amount;

    CheckIngredientIn(int IngId, int amount) {
        this.IngId = IngId;
        this.amount = amount;
    }
}
