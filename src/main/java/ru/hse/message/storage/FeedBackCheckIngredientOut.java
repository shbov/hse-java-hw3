package ru.hse.message.storage;

import ru.hse.message.Message;

public class FeedBackCheckIngredientOut extends Message {
    public Boolean success;

    public FeedBackCheckIngredientOut(Boolean success) {
        this.success = success;
    }
}
