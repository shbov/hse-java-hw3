package ru.hse.message.visitor;

import lombok.Getter;
import ru.hse.message.Message;

public class RequestTimeOut extends Message {
@Getter
private int idVisitor;
    public RequestTimeOut(int idVisitor){
        this.idVisitor=idVisitor;
    }
}
