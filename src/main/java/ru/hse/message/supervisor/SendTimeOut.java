package ru.hse.message.supervisor;

import lombok.Getter;
import ru.hse.message.Message;

public class SendTimeOut extends Message {
    @Getter
    private long time;
    public SendTimeOut(long time){
        this.time=time;
    }
}
