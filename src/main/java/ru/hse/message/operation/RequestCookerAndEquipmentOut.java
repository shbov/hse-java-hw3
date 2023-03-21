package ru.hse.message.operation;

import ru.hse.message.Message;

//запрашивает управляющего агента зарезервировать агента повара
// и агента оборудования для выполнения операции процесса.
public class RequestCookerAndEquipmentOut extends Message {
    // просим у системы выделить любоо повара и заданное оборудование
    String EquipmentType;

    RequestCookerAndEquipmentOut(String EquipmentType) {
        this.EquipmentType = EquipmentType;
    }
}
