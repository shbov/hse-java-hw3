package ru.hse.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Agent;
import ru.hse.agent.AgentRepository;

@UtilityClass
@Slf4j
/** Утилита для работы с агентами */
public class AgentUtility {

  /**
   * Получаем уникальный айди агента
   *
   * @param className Класс агента
   * @return айди агента
   * @param <T> Агент
   */
  public static synchronized <T extends Agent<?>> int generateID(Class<T> className) {
    int possibleId = AgentRepository.findByType(className).size() + 1;

    while (AgentRepository.isAgentExist(className, possibleId)) {
      ++possibleId;
    }

    return possibleId;
  }
}
