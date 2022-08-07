package com.example.demo.Telegram_Bot.States;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Абстракция состояний бота , имеет метод который переопределяется в зависимости от состояния бота
 */

public interface States {



    abstract void executeState(Update update, Long chatId, String messageText, String userFirstName);

}
