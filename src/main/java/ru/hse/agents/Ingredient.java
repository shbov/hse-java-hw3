package ru.hse.agents;

public class Ingredient extends Agent {
    int weight;
    String name;

    public Ingredient(int id, String name, int weight, SuperVisor supervisor) {
        super(id, supervisor);
        this.weight = weight;
        this.name = name;
    }

    @Override
    protected void proceed(Object o) throws Exception {
        //TODO какие команды приходят продукту
    }
}
