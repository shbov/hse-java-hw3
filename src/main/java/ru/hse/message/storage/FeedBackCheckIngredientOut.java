package ru.hse.message.storage;

import ru.hse.message.Message;

public class FeedBackCheckIngredientOut extends Message {
    Boolean success;

    FeedBackCheckIngredientOut(Boolean success) {
        this.success = success;
    }
}
