package ru.hse.message.cooker;

import lombok.Getter;
import lombok.Setter;
import ru.hse.agent.Operation;
import ru.hse.message.Message;

// удаляем меняем назначаем опреацию
public class ChangeOperationIn extends Message {

    @Getter
    @Setter
    private Operation operation;

    public ChangeOperationIn(Operation operation) {
        this.operation = operation;
    }
}
