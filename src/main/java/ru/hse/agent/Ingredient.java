package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;import ru.hse.utilities.AgentUtility;

@Slf4j
@ToString(callSuper = true)
public class Ingredient extends Agent {
    @Getter
    private int productId;
    @Getter
    @Setter
    private double quantity;
    @Getter
    private int supervisorId;

    public Ingredient() {
    }

    public Ingredient(int productId, double quantity, int supervisorId) {
        setId(AgentUtility.generateID(Ingredient.class));

        this.productId = productId;
        this.quantity = quantity;
        this.supervisorId = supervisorId;
    }

    @Override
    protected void proceed(Message message) throws Exception {
    }

    @JsonProperty("name")
    private void unpackName(String name) {
        setName(name);
    }

    @JsonProperty("id")
    private void unpackId(int id) {
        setId(id);
    }
}
