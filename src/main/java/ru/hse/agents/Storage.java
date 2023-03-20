package ru.hse.agents;

import java.util.List;
import java.util.Map;

public class Storage extends Agent{
    public List<Ingredient> activeIngredients;
    public Map<Integer,Integer> storage;
    public Storage(int id, SuperVisor supervisor,List<Ingredient> activeIngredients,Map<Integer,Integer> storage) {
        super(id, supervisor);
        this.activeIngredients=activeIngredients;
        this.storage=storage;
        //TODO «Проверяет», имеется ли в наличии на складе достаточный объем конкретного продукта
        // для резервирования под экземпляр блюда / напитка для выполнения определенного заказа.
        // Если такая проверка проходит успешно при получении приказа о резервировании
        // от управляющего агента, создает агент продукта.
    }

    @Override
    protected void proceed(Object o) throws Exception {

    }
}
