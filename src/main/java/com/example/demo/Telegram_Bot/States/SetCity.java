package com.example.demo.Telegram_Bot.States;

import com.example.demo.Contsants.BotState;
import com.example.demo.Telegram_Bot.WeatherBotFacade;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

@Component
public class SetCity extends WeatherBotFacade implements States {
    @SneakyThrows
    //  @Override
    public void executeState(Update update, Long chatId, String messageText, String userFirstName) {
        // проверка - существует ли введенный пользователем город
        System.out.println("9999999999999");
        if (weatherService.isCity(messageText.toLowerCase(Locale.ROOT))) {
            System.out.println(messageText);
            System.out.println("2222222222");
            chatConfigService.setCity(chatId, messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT));
            System.out.println(messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT));
            chatConfigService.setBotState(chatId, BotState.DEFAULT);
            sendMessage(update, messageGenerator.generateSuccessSetCity(chatConfigService.getCity(chatId)));
        } else sendMessage(update, messageGenerator.generateErrorCity());
    }
}
