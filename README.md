# WeatherTelegramBot
Weather_Telegram_Bot https://t.me/UltraGangstaMegaBot

In developing

Beta_Build

Бот создан на Spring Boot

После начала чата с ботом , бот создает сущность чата в БД в которой хранится чат ID, город и состояния бота, для каждого чата устанавливается свое состояние в зависимости от действия проводимых с ботом. Каждый раз когда пользователь пишет комманду боту, бот через сервисы обращается к БД за состояние и городом если нужно и в зависимости от полученных данных ведет себя соответсвено состоянию.

В пакете States описаны состояния бота , для добавления новых состояний просто требуется создать соответствующий класс описывающий поведение состояния.

Весь проект находится по адресу ssrc\main\java\com\example\demo

Для взаимодействия с Telegram API используется библиотека telegrambots

Логика бота описана в пакете Telegram_Bot

Метеоданные получаются через запрос на погодный API

Реализация обращения к погодному API описана в пакете DAO

В пакете Entity описана модель сущности для хранения в БД

В пакете Service описаны сервисы которыми пользуется бот

В пакете описаны классы на основе которых джексон создает сущности из JSON ответа

Добавлена возможность делать прогноз погоды
