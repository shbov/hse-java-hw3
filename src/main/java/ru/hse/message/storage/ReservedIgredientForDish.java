package ru.hse.message.storage;

import ru.hse.message.Message;

public class ReservedIgredientForDish extends Message {
    public int IngId;
    public int amount;
    public String name;

    public ReservedIgredientForDish(int IngId, String name, int amount) {
        this.IngId = IngId;
        this.name=name;
        this.amount = amount;
    }
}
