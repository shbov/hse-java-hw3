package ru.hse.message.visitor;

import ru.hse.agent.Dish;
import ru.hse.message.Message;

import java.util.List;

public class RequestMenuOut extends Message {
    public int idVisitor;

    public RequestMenuOut( int idVisitor) {
        this.idVisitor = idVisitor;
    }
}
