package ru.hse.message.order;

import ru.hse.message.Message;

import java.util.List;

public class RequireWaitingTimeFromProcessOut extends Message {
    public List<Process> processes;

    public RequireWaitingTimeFromProcessOut(List<Process> processes) {
        this.processes = processes;
    }
}
