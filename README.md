# Low-Key Students Forum

Low-Key Students — это проект, представляющий собой форум для студентов Кыргызстана. Проект разделен на две части: `Frontend` и `Backend`, которые взаимодействуют через REST API.

## О проекте

Low-Key Students позволяет пользователям:
- Зарегистрироваться и войти в систему
- Создавать, редактировать и удалять посты
- Комментировать посты
- Просматривать профили пользователей

## Технологии

### Backend:
- **Язык**: Java
- **Фреймворк**: Spring Boot
- **База данных**: PostgreSQL
- **Безопасность**: Spring Security, JWT
- **Дополнительно**: Lombok, MapStruct

### Frontend:
- **Язык**: JavaScript
- **Стек**: HTML, CSS, Vanilla JavaScript
- **Взаимодействие**: Fetch API для работы с REST API

## Установка и запуск

### 1. Backend
- Перейдите в директорию `backend` и выполните команды:
  ```bash
  ./mvnw clean install
  ./mvnw spring-boot:run
Приложение будет доступно по адресу: http://localhost:8081

### 2. Frontend
- Перейдите в директорию `frontend` и откройте файл `index.html` в браузере.
