package com.example.demo.Telegram_Bot.States;

import com.example.demo.Contsants.BotState;
import com.example.demo.Contsants.MainCommand;
import com.example.demo.Telegram_Bot.WeatherBotFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 * Классописывает фабрику состояний для реализаций действий бота соответсвующих каждому состоянию
 */
@Component
public class StatesFacoty extends WeatherBotFacade {


    @Autowired
    ADefault aDefault = new ADefault();
    @Autowired
    Now now = new Now();
    @Autowired
    Search_now search_now = new Search_now();
    @Autowired
    SetCity setcity = new SetCity();
    @Autowired
    ForecastWeather forecastWeather = new ForecastWeather();
    @Autowired
    ForecastWeatherSearch forecastWeatherSearch = new ForecastWeatherSearch();


    public States statesFactory(Update update, Long chatId, String messageText, String userFirstName) {

        System.out.println(chatConfigService.getBotState(chatId));
        BotState botState = chatConfigService.getBotState(chatId);
        States states = null;

        if (messageText.equals(MainCommand.START.name())) {
            chatConfigService.setBotState(chatId, BotState.DEFAULT);
            sendMessage(update, messageGenerator.generateStartMessage(userFirstName));

        }

        // /cancel Возвращение бота в состояние DEFAULT (отмена текущей команды)
        if (messageText.equals(MainCommand.CANCEL.name())) {
            if (botState == BotState.DEFAULT) {
                sendMessage(update, "Нет активной команды для отклонения");
            } else {
                chatConfigService.setBotState(chatId, BotState.DEFAULT);
                sendMessage(update, messageGenerator.generateSuccessCancel());

            }
        }

        if (botState == BotState.DEFAULT) {
            states = aDefault;
        }

        if (botState == BotState.NOW) {
            states = now;
        }
        if (botState == BotState.SEARCH_NOW) {
            states = search_now;
        }
        if (botState == BotState.SET_CITY) {
            states = setcity;
        }
        if (botState == BotState.FORECAST) {
            states = forecastWeather;
        }
        if (botState == BotState.FORECAST_NOW)
            states = forecastWeatherSearch;

        return states;
    }

    private Long setChatIdToMessageBuilder(Update update, SendMessage.SendMessageBuilder messageBuilder) {
        Long chatId = null;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            messageBuilder.chatId(update.getMessage().getChatId().toString());
        } else if (update.hasChannelPost()) {
            chatId = update.getChannelPost().getChatId();
            messageBuilder.chatId(update.getChannelPost().getChatId().toString());
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            messageBuilder.chatId(update.getCallbackQuery().getMessage().getChatId().toString());
        }
        return chatId;
    }

//    public void sendMessage(Update update, String messageText) {
//        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
//
//        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);
//
//        messageBuilder.text(messageText);
//
//        try {
//            weatherBot.execute(messageBuilder.build());
//        } catch (TelegramApiException telegramApiException) {
//            telegramApiException.printStackTrace();
//        }
//    }
//
//    public void sendMessage(Update update, String messageText, KeyboardType keyboardType) {
//        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
//
//        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);
//
//        messageBuilder.text(messageText);
//
//        switch (keyboardType) {
//            case CITY_CHOOSE: {
//                //устанавливаем кнопки, созданные выше
//                messageBuilder.replyMarkup(keyboardService.setChooseCityKeyboard(chatId));
//                break;
//            }
//        }
//
//        try {
//            weatherBot.execute(messageBuilder.build());
//        } catch (TelegramApiException telegramApiException) {
//            telegramApiException.printStackTrace();
//        }
//    }

}
