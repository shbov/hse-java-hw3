package ru.hse.agent;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс для доступа ко всем агентам
 */
@UtilityClass
@Slf4j
public class AgentRepository {

    @Getter
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
    @SuppressWarnings("unchecked")
    public static synchronized <T extends Agent<?>> List<T> findByType(Class<T> type) {
        return AGENTS.stream()
                .filter(agent -> agent.getClass() == type)
                .map(agent -> (T) agent)
                .toList();
    }

    /**
     * Проверяем, есть ли агент в репозитории
     *
     * @param type Агент
     * @param id   Айди
     * @param <T>  Класс, наследуемый от агента
     * @return true, если агент найден; иначе – false
     */
    public static synchronized <T extends Agent<?>> boolean isAgentExist(Class<T> type, int id) {
        return AGENTS.stream().anyMatch(agent -> agent.getId() == id && agent.getClass() == type);
    }

    /**
     * Ищем нужного агента в репозитории по классу и айди
     *
     * @param type Агент
     * @param id   Айди агента
     * @param <T>  Класс, наследуемый от агента
     * @return найденный агент в репозитории или null;
     */
    public static <T extends Agent<?>> Agent<?> findByTypeAndId(Class<T> type, int id) {
        return AGENTS.stream()
                .filter(agent -> agent.getId() == id && agent.getClass() == type)
                .findFirst()
                .orElse(null);
    }
}
