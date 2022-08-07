package com.example.demo.Telegram_Bot.States;

import com.example.demo.Contsants.BotState;
import com.example.demo.Telegram_Bot.WeatherBotFacade;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Now extends WeatherBotFacade implements States {
    //  @Override
    public void executeState(Update update, Long chatId, String messageText, String userFirstName) {

        // если выбран не стандартный город

        if (messageText.equals(keyboardService.getChooseCityNowButtonData().toUpperCase())) {
            chatConfigService.setBotState(chatId, BotState.SEARCH_NOW);
        }

        // погода для стандартного города
        else {

            chatConfigService.setBotState(chatId, BotState.DEFAULT);
            sendMessage(update, messageGenerator.generateCurrentWeather(chatConfigService.getCity(chatId)));
        }


    }
}
