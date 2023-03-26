package ru.hse.agent;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.message.Message;
import ru.hse.message.storage.CheckIngredientIn;
import ru.hse.message.storage.FeedBackCheckIngredientOut;
import ru.hse.message.storage.RefreshMenu;
import ru.hse.message.storage.ReservedIgredientForDish;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@ToString(callSuper = true)
public class Storage extends Agent {
    @Getter
    private List<Product> products = new CopyOnWriteArrayList<>();

    public Storage(int id, SuperVisor supervisor) {
        super(id, supervisor);
    }

    @Override
    protected void proceed(Message message) throws Exception {
        if (message instanceof CheckIngredientIn checkIngredientIn) {
            Product product = products.stream()
                    .filter(p -> p.getId() == checkIngredientIn.getId())
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                if (product.getQuantity() > checkIngredientIn.getQuantity()) {
                    log.info("Checked product" + true);
                    Message respond = new FeedBackCheckIngredientOut(true);
                } else {
                    log.info("Checked product" + false);
                    Message respond = new FeedBackCheckIngredientOut(false);
                }
            }
            Message respond = new FeedBackCheckIngredientOut(false);
        } else if (message instanceof ReservedIgredientForDish reservedIgredientForDish) {
            Ingredient ingredient = new Ingredient(
                    AgentUtility.generateID(Ingredient.class),
                    reservedIgredientForDish.getQuantity(),
                    this.getSupervisor().getId());
            log.info("Reserved ingredient: id " + ingredient.getId() + ", quantity:" + ingredient.getQuantity());
            Ingredient.start(ingredient);
            Product product = products.stream()
                    .filter(p -> p.getType() == reservedIgredientForDish.getIngId())
                    .findFirst()
                    .orElse(null);
            product.setQuantity(product.getQuantity() - reservedIgredientForDish.getQuantity());
        } else if (message instanceof RefreshMenu refreshMenu) {
            List<Dish> availableDish = new CopyOnWriteArrayList<>();
            for (Dish dish : refreshMenu.getDishes()) {
                boolean f = true;
                for (Operation oper : dish.getOperations()) {
                    for (Ingredient ing : oper.getProducts()) {
                        Product outProduct = products.stream().filter(prod -> prod.getType() == ing.getId())
                                .findFirst()
                                .orElse(null);
                        if (outProduct != null && outProduct.getQuantity() > outProduct.getQuantity()) {
                            f = false;
                        }
                    }
                }
                if (f) {
                    availableDish.add(dish);
                }
            }
            Message respond = new RefreshMenu(availableDish, refreshMenu.getIdVisitor());
            log.info("Storage checked ingredients");
            getSupervisor().registerMessage(respond);
        } else {
            log.error("Message not acceptable " + message.getClass().toString());
        }
    }

    public void initStorage() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/products.json");
            String json = IOUtils.toString(fis, StandardCharsets.UTF_8);

            products =
                    DeserializeUtility.deserializeListOfObjects(json, "products", new TypeReference<>() {
                    });
            if (products == null) {
                products = new ArrayList<>();
                log.warn("список продуктов пуст!");
            }
            products.forEach(System.out::println);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        Storage.start(this);
    }
}
