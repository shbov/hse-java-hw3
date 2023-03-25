package ru.hse.message.storage;

import lombok.Getter;
import ru.hse.message.Message;

public class CheckIngredientIn extends Message {
    @Getter
    private int IngId;

    @Getter
    private int quantity;

    public CheckIngredientIn(int IngId, int amount) {
        this.IngId = IngId;
        this.quantity = amount;
    }
}
