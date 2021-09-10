# SubWeb

Современный человек постоянно сталкивается со множеством метаданных, ссылок, контактов, заметок,
адресов. Они всплывают спонтанно и хранятся в приложениях разрознено. На самом деле, это большая
проблема и поэтому рано или поздно вы заметите следующие симптомы:

1. Ваши данные перестали вмещаться в обозреваемую структуру приложения.
2. Вы уже удалили "ненужную" закладку или контакт, чтобы очистить обозреваемую структуру.
3. Сложность поиска суммарных данных по конкретному человеку или компании возросла до `O(N)`,
   где `N` — количество ваших мессенджеров, почт, органайзеров и т. п.
4. Знаете что вам нужно, но поиск ничего не находит.

Проект SubWeb призван сделать все заметки, контакты и закладки из разных источников снова
обозреваемыми, упорядоченными и легко находимыми. В основе приложения лежит концепция
минимально-возможных атомарных характеристик, тегов. По сути, "тег" это просто кусочек текста,
который мы наделяем смыслом. Теги могут быть обще-описательными, вроде заметки о характере или
должности, либо однозначными идентификаторами, вроде ИНН или номера (такие теги на входе проходят
нормализацию для лучшего поиска совпадений). В совокупности, теги образуют сущности побольше, вроде
человека или организации. SubWeb обнаруживает сущности во входящих данных из разных источников,
разбивает сущности на наборы тегов, а затем выявляет пересечения идентификаторов сущностей из разных
источников, и объединяет такие сущности в одну, обогащая поисковую выдачу. Ключевые функции:

1. Поиск сущностей по набору тегов. Бот попросит вас внести дополнительные теги, чтобы сузить
   выдачу.
2. Возможность добавить собственный тег к любой сущности. Вам будет предложено добавить заметку,
   почту номер или другой тип тега к результату выдачи.
3. Возможность загрузить в систему новую сущность. Бот попросит вас внести теги, чтобы максимально
   конкретно идентифицировать сущность, избежав слияния с существующими. Можно загрузить массив
   сущностей автоматически, через файл экспорта Google контактов или закладок Firefox.
5. Постоянно происходит фоновая агрегация сущностей на основе полного вхождения ключевых
   идентификаторов (номер, документ), и вдобавок поиск общих синтетических мета-сущностей, вроде
   организации/группы/секты объединяющий существующие сущности на основе описательных тегов.
6. Вы можете получить полный дамп состояния системы в каждый момент времени.

## Build and run

`REDIS_STRING`    — Redis database connection string.

`TELEGRAM_TOKEN`  — Telegram Bot API token.

### With Java

Execute `./gradlew clean build`. Your jar will be located at `./build/libs` with `-all.jar` postfix.
Now you can run:

```shell
REDIS_STRING=... TELEGRAM_TOKEN=... java -jar SubWeb-all.jar
```

### With Docker

Execute `docker build . -t subweb`. Your image will be located at `docker images -a`. Now you can
run:

```shell
docker run -it --rm --env TELEGRAM_TOKEN=... --env REDIS_STRING=... subweb
```

## Deploy

[![Deploy to DO](https://www.deploytodo.com/do-btn-blue-ghost.svg)](https://cloud.digitalocean.com/apps/new?repo=https://github.com/demidko/service/tree/main)

## Code style

1. No classes, but DTO (POD).
2. No primitives, but DTO (POD).
3. No methods, but pure functions. Every function should be located in companion object of returned
   type.


