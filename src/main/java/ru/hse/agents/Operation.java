package ru.hse.agents;

public class Operation extends Agent{
    public Cooker cook;
    public int minut;
    public KitchenEquipment equipment;
    public Operation(int id, SuperVisor supervisor, Cooker cook, KitchenEquipment equipment, int minut) {
        super(id, supervisor);
        this.cook=cook;
        this.equipment=equipment;
        this.minut=minut;
    }

    @Override
    protected void proceed(Object o) throws Exception {
        //TODO запрашивает управляющего агента зарезервировать агента повара
        // и агента оборудования для выполнения операции процесса

    }
    // продукты для операции
}
