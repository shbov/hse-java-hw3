package ru.hse.message.ingredient;

import ru.hse.message.Message;

public class ReservateIngredientIn extends Message {
    int IngId;
    int amount;

    ReservateIngredientIn(int IngId, int amount) {
        this.IngId = IngId;
        this.amount = amount;
    }
}
