package ru.hse.Message.MessageForSuperVisor;

import ru.hse.Message.Message;
import ru.hse.agents.Dish;

import java.util.List;

public class SendMenu  extends Message {
    public List<Dish> dishes;
}
