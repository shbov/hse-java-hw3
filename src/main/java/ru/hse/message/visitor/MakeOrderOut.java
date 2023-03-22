package ru.hse.message.visitor;

import ru.hse.agent.Dish;
import ru.hse.message.Message;

import java.util.List;

public class MakeOrderOut extends Message {
    public List<Dish> dishes;
    MakeOrderOut(List<Dish> dishes){
        this.dishes=dishes;
    }
}
