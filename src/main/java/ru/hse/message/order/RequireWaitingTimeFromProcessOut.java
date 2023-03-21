package ru.hse.message.order;

import ru.hse.message.Message;

import java.util.List;

public class RequireWaitingTimeFromProcessOut extends Message {
    List<Process> processes;

    RequireWaitingTimeFromProcessOut(List<Process> processes) {
        this.processes = processes;
    }
}
