#  Мультиагентная система управления рестораном
Шубников Андрей, БПИ216\
Степашкина Виталия, БПИ216

### Сборка проекта: ```mvn clean install```
Проект будет доступен в папке ```/target/hse-hw3/```\
Запуск приложения: ```java -jar app.jar```\
Также последняя версия билда доступна в гите в папке /target/hse-hw3 
### Основное взаимодействие
ООП-реализация MAS симуляция работы ресторана
Каждый агент работает независимо в своем потоке 
Взаимодействие осуществляется через класс сообщений и AgentRepository registerMessage и proceed
(как в примере с гита)

Реализовано дополнительно оценка времени ожидания заказов 
(в момент старта и в любое другое время)
(В программе Посетитель запрашивает ожидаемое время в начале и через random время после этого)

Присутствует логирование всех процессов с экспортов в json формат

Также происходит проверка корректности и соответствия json файлов при десериализации,
при обработке сообщений и тд

Была предпринята усердная попытка по внедрению многопоточности, 
а точнее контролировании (и так все идет в разных потоках) добавлены notifyall, synchrohized-метод 




### Работа с API-сервером:

Используем библиотеку Spark. Информация о сервере появляется в логах и консоли при загрузке
приложения.

Доступные методы:
- GET method "/api/logs"\
Возвращает json-объектов с логами
- POST method "/api/visitor-order"\
Принимает на вход единственный параметр "visitor" следующего формата: ```{"name": "Visitor from API", "id": 101}```\
Пример запроса: ```http://localhost:4567/api/visitor-order?visitor={"name": "Visitor from API", "id": 101}```
Пример ответа: ```{"message": "Спасибо за заказ! Он уже в работе.", "status": "success"}```\
Если формат запроса отличается от эталонного/ошибка при создании объекта, то выводится следующее сообщение: ```{"message": "request is invalid", "status": "error"}```