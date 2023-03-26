package ru.hse.message.storage;

import lombok.Getter;
import ru.hse.message.Message;

public class ReservedIgredientForDish extends Message {
    @Getter
    private int IngId;
    @Getter
    private double quantity;

    public ReservedIgredientForDish(int IngId, double quantity) {
        this.IngId = IngId;
        this.quantity = quantity;
    }
}
