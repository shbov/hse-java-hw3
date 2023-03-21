package ru.hse.message.cooker;

import ru.hse.message.Message;

// удаляем меняем назначаем опреацию
public class ChangeOperationIn extends Message {
    String readinessStage = null;

    ChangeOperationIn(String readinessStage) {
        this.readinessStage = readinessStage;
    }
}
