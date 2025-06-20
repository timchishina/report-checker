#  Report Checker — микросервисное приложение для анализа студенческих отчётов


---

## Описание

Приложение представляет собой систему анализа текстовых отчётов студентов.  
Система позволяет:

- Загружать `.txt` отчёты
-  Получать статистику текста (абзацы, слова, символы)
-  Работать через маршрутизатор Gateway
-  Хранить метаданные файлов в PostgreSQL
-  Документировать API через Swagger

---

##  Архитектура

Микросервисная архитектура с чётким разделением ответственности:

| Микросервис         | Порт  | Ответственность                              |
|---------------------|-------|-----------------------------------------------|
| `api-gateway`       | 8080  | Маршрутизация запросов к другим сервисам      |
| `file-storage`      | 8081  | Загрузка и хранение файлов, PostgreSQL        |
| `file-analysis`     | 8082  | Анализ текста: абзацы, слова, символы         |

---

##  Быстрый запуск через Docker

###  Требования

- Docker
- Docker Compose

###  Сборка и запуск

```bash
mvn clean package -DskipTests
docker-compose up --build
```
# Внутренние технологии
Java 17

Spring Boot 3.1.9

Spring Data JPA

PostgreSQL

Spring Cloud Gateway

Swagger / OpenAPI

Jacoco (Code Coverage)

Docker + Docker Compose
# Покрытие тестами
Используется JUnit 5 и Spring Boot Test

Jacoco подключён

Покрытие: 65%+