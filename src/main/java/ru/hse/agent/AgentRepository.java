package ru.hse.agent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/** Класс для доступа ко всем агентам */
@UtilityClass
@Slf4j
public class AgentRepository {
  private static final List<Agent<?>> AGENTS = new CopyOnWriteArrayList<>();

  /**
   * регистрирует нового агента, чтобы другие могли его найти
   *
   * @param agent регистрируемый агент
   */
  public static synchronized void register(Agent<?> agent) {
    if (!AGENTS.contains(agent)) {
      AGENTS.add(agent);
    }

    log.info("Agent registered: {}", agent.getName());
  }

  /**
   * удаляет агента, когда его работа завершена
   *
   * @param agent удаляемый агент
   */
  public static synchronized void remove(Agent<?> agent) {
    AGENTS.remove(agent);

    log.info("Agent removed from repository: {}", agent.getName());
  }

  /**
   * Находит всех агентов искомого типа
   *
   * @param type тип искомого агента
   * @return список всех рабочих агентов
   */
  public static synchronized <T extends Agent<?>> List<T> findByType(Class<T> type) {
    return AGENTS.stream()
        .filter(agent -> agent.getClass() == type)
        .map(agent -> (T) agent)
        .toList();
  }

  public static synchronized <T extends Agent<?>> boolean isAgentExist(Class<T> type, int id) {
    return AGENTS.stream().anyMatch(agent -> agent.getId() == id && agent.getClass() == type);
  }
}
