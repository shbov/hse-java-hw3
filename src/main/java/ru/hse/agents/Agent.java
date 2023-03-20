package ru.hse.agents;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.hse.Message.Message;

/**
 * Класс представляющий агента в МАС
 *
 * @param <MessageType> тип обрабатываемых сообщений
 */
@Slf4j
public abstract class Agent<Message> implements Runnable {
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    @Getter
    private String name;
    private Thread workingThread;

    public Agent(int id, SuperVisor supervisor) {
    }

    /**
     * Запускает агента
     */
    public synchronized static void start(Agent<?> agent) {
        agent.name = agent.generateName();
        AgentRepository.register(agent);
        agent.workingThread = new Thread(agent);
        agent.workingThread.start();
    }

    /**
     * Останавливает работу агента
     */
    public synchronized static void stop(Agent<?> agent) {
        AgentRepository.remove(agent);
        agent.workingThread.interrupt();
    }

    /**
     * переопределяем в конкретных агентах этот метод, программируя их поведение
     * @param message обрабатываемое сообщение
     */
    abstract protected void proceed(Message message) throws Exception;

    /**
     * Передает сообщение агенту
     * @param message сообщение для агента
     */
    public void registerMessage(Message message) {
        messageQueue.add(message);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();

                log.debug("Agent {} received message {}", this.name, message.getId());
                proceed(message);
                log.debug("Agent {} completed message {}", this.name, message.getId());

            } catch (InterruptedException e) {
                log.info("Agent {} was interrupted", name);
                break;
            } catch (Exception e) {
                log.error("An error occurred in Agent {}", name, e);
                break;
            }
        }
    }

    private synchronized String generateName() {
        return this.getClass().getSimpleName() + "-" + AgentRepository.findByType(this.getClass()).size();
    }
}
