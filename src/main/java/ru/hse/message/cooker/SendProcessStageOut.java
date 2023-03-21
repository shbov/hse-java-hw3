package ru.hse.message.cooker;

import ru.hse.message.Message;

public class SendProcessStageOut extends Message {
    String readinessStage = null;

    SendProcessStageOut(String readinessStage) {
        this.readinessStage = readinessStage;
    }
}
