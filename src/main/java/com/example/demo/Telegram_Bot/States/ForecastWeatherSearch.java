package com.example.demo.Telegram_Bot.States;

import com.example.demo.Contsants.BotState;
import com.example.demo.Telegram_Bot.WeatherBotFacade;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@Component
public class ForecastWeatherSearch extends WeatherBotFacade implements States {

    @SneakyThrows
    @Override
    public void executeState(Update update, Long chatId, String messageText, String userFirstName) {
        //проверка на существование города
        if (!weatherService.isCity(messageText)) {
            sendMessage(update, messageGenerator.generateErrorCity());
        }

        // погода для введенного города
        else {
            sendMessage(update, messageGenerator.generateForecastWeather(messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT)));
            chatConfigService.setBotState(chatId, BotState.DEFAULT);
        }
    }
}
