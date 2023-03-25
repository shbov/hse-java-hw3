package ru.hse;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import ru.hse.agent.Agent;
import ru.hse.agent.Operation;
import ru.hse.agent.SuperVisor;
import ru.hse.agent.Visitor;
import ru.hse.message.Message;
import ru.hse.message.visitor.RequestMenuOut;
import ru.hse.utilities.AgentUtility;
import ru.hse.utilities.DeserializeUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class Main {
    @Getter
    private static List<Operation> myOperations;

    public static void main(String[] args) throws IOException, InterruptedException {
        ApiServer.start();
        initOperations();

        SuperVisor superVisor = new SuperVisor(AgentUtility.generateID(SuperVisor.class));
        Agent.start(superVisor);

        FileInputStream fis = new FileInputStream("src/main/resources/visitors.json");
        String json = IOUtils.toString(fis, StandardCharsets.UTF_8);
        List<Visitor> visitors = DeserializeUtility.deserializeListOfObjects(json, "visitors", new TypeReference<>() {
        });
        for (Visitor vis : visitors) {
            Visitor.start(vis);
            Message mess = new RequestMenuOut(vis.getId());
            superVisor.registerMessage(mess);
            Thread.sleep(100);
        }
        Agent.stop(superVisor);
        visitors.forEach(Visitor::stop);
    }

    private static void initOperations() throws IOException {
        FileInputStream fisOp = new FileInputStream("src/main/resources/operation_log.json");
        String jsonOp = IOUtils.toString(fisOp, StandardCharsets.UTF_8);
        myOperations =
                DeserializeUtility.deserializeListOfObjects(jsonOp, "operations", new TypeReference<>() {
                });
        myOperations.forEach(System.out::println);
    }
}