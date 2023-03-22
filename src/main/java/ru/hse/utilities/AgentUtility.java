package ru.hse.utilities;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.hse.agent.Agent;
import ru.hse.agent.AgentRepository;

@UtilityClass
@Slf4j
public class AgentUtility {
  public static synchronized <T extends Agent<?>> int getID(Class<T> className) {
    return AgentRepository.findByType(className).size();
  }
}
