package ru.hse.agents;

public class SuperVisor extends Agent{

    public SuperVisor(int id, SuperVisor supervisor) {
        super(id, supervisor);
    }

    @Override
    protected void proceed(Object o) throws Exception {
        //TODO он тут главная шишка всеми помыкает
    }
}
