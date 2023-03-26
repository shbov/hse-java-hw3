package ru.hse.message.storage;

import lombok.Getter;
import ru.hse.agent.Dish;
import ru.hse.message.Message;

import java.util.List;

public class RefreshMenu extends Message {
    @Getter
    private List<Dish> dishes;
    @Getter
    private int idVisitor;

    public RefreshMenu(List<Dish> dishes, int idVisitor) {
        this.dishes = dishes;
        this.idVisitor=idVisitor;
    }
}
