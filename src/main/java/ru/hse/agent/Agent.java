package ru.hse.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import ru.hse.message.Message;

/**
 * Класс представляющий агента в МАС
 *
 * @param <MessageType> тип обрабатываемых сообщений
 */
@Slf4j
@ToString(callSuper = true)
public abstract class Agent<MessageType extends Message> implements Runnable {
  private final BlockingQueue<MessageType> messageQueue = new LinkedBlockingQueue<>();
  @JsonIgnore @Getter private final SuperVisor supervisor;

  @JsonProperty("id")
  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  @JsonProperty("name")
  private String name;

  @Getter @JsonIgnore private Thread workingThread;

  public Agent() {
    supervisor = null;
    id = 0;
  }

  public Agent(int id, SuperVisor supervisor) {
    this.id = id;
    this.supervisor = supervisor;
  }

  /** Запускает агента */
  public static synchronized void start(Agent<?> agent) {
    agent.name = agent.generateName();
    AgentRepository.register(agent);

    agent.workingThread = new Thread(agent);
    agent.workingThread.start();

    log.info("Agent {} started", agent.getName());
  }

  /** Останавливает работу агента */
  public static synchronized void stop(Agent<?> agent) {
    AgentRepository.remove(agent);
    agent.workingThread.interrupt();

    log.info("Agent {} finished", agent.getName());
  }

  /**
   * переопределяем в конкретных агентах этот метод, программируя их поведение
   *
   * @param message обрабатываемое сообщение
   */
  protected abstract void proceed(MessageType message) throws Exception;

  /**
   * Передает сообщение агенту
   *
   * @param message сообщение для агента
   */
  public void registerMessage(MessageType message) {
    messageQueue.add(message);
  }

  @Override
  public void run() {
    while (true) {
      try {
        MessageType message = messageQueue.take();

        log.info("Agent {} received message {}", this.name, message.getId());
        proceed(message);
        log.info("Agent {} completed message {}", this.name, message.getId());

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
    return this.getClass().getSimpleName()
        + "-"
        + getId();
  }
}
