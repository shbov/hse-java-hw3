package ru.hse.message.cooker;

import ru.hse.agent.Operation;
import ru.hse.message.Message;

// удаляем меняем назначаем опреацию
public class ChangeOperationIn extends Message {
    public Operation operation;

    public ChangeOperationIn(Operation operation) {
        this.operation = operation;
    }
}
