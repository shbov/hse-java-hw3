package ru.hse.message.supervisor;

import ru.hse.message.Message;

public class SendChequeOut  extends Message {
    int sumOfMoney;
    SendChequeOut(int sumOfMoney){
        this.sumOfMoney=sumOfMoney;
    }
}
