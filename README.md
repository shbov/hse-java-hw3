# hse-java-hw1

### Сборка проекта: ```mvn clean install```
Проект будет доступен в папке ```/target/hse-hw3/```\
Запуск приложения: ```java -jar app.jar```

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