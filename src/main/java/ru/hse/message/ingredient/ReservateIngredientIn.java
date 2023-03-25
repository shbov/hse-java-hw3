package ru.hse.message.ingredient;

import lombok.Getter;
import ru.hse.message.Message;

public class ReservateIngredientIn extends Message {
    @Getter
    private int ingId;
    @Getter
    private double quantity;

    public ReservateIngredientIn(int IngId, double amount) {
        this.ingId = IngId;
        this.quantity = amount;
    }
}
