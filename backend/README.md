---

### **`README.md` для Backend**

```markdown
# Low-Key Students — Backend

Backend часть проекта разработана с использованием Spring Boot и предоставляет REST API для работы с форумом.

## Описание функционала
- **Пользователи**:
  - Регистрация и аутентификация
  - Получение публичных данных пользователя
- **Посты**:
  - Создание, редактирование, удаление
  - Привязка постов к автору
- **Комментарии**:
  - Добавление и удаление комментариев
  - Привязка комментариев к посту и пользователю
- **Безопасность**:
  - JWT для аутентификации
  - Защита эндпоинтов через Spring Security

## Установка и запуск
1. Настройте базу данных PostgreSQL:
   ```sql
   CREATE DATABASE lowkeystudents;
2. В файле application.yaml укажите настройки подключения к БД.

3. Соберите и запустите приложение:

./mvnw clean install
./mvnw spring-boot:run

4. Backend будет доступен по адресу: http://localhost:8081

## Структура проекта
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── alatoo.edu.kg.lowkeystudents/
│   │   │       ├── api/
│   │   │       ├── store/
│   │   │       └── ...
│   │   └── resources/
│   │       ├── application.yaml
│   │       └── ...
├── pom.xml
└── README.md
