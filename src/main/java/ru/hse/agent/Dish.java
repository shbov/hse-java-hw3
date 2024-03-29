package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.Main;
import ru.hse.message.Message;

import java.util.List;

@Slf4j
@ToString(callSuper = true)
public class Dish extends Agent {
    @Getter
    @JsonProperty("dish_descr")
    private String description;

    @Getter
    private List<Operation> operations;

    public Dish() {
    }

    public Dish(int id, SuperVisor supervisor, List<Operation> operations) {
        super(id, supervisor);
        this.operations = operations;
    }

    public void getCost() {
    }

    @Override
    protected void proceed(Message o) throws Exception {
    }

    @JsonProperty("name")
    private void unpackName(String name) {
        setName(name);
    }

    @JsonProperty("id")
    private void unpackId(int id) {
        setId(id);
    }

    @JsonProperty("operations")
    private void unpackOperations(List<Integer> operations) {
        this.operations = operations.stream()
                .map(
                        id ->
                                Main.getMyOperations().stream()
                                        .filter(so -> so.getId() == id)
                                        .findFirst()
                                        .orElse(null))
                .toList();
    }
}
