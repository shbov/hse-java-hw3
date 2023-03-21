package ru.hse.message.ingredient;

import ru.hse.message.Message;

public class FeedBackIngredientOut extends Message {
    Boolean success;

    FeedBackIngredientOut(Boolean success) {
        this.success = success;
    }
}
