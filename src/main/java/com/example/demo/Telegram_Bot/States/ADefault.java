package com.example.demo.Telegram_Bot.States;

import com.example.demo.Contsants.BotState;
import com.example.demo.Contsants.KeyboardType;
import com.example.demo.Contsants.MainCommand;
import com.example.demo.Telegram_Bot.WeatherBotFacade;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ADefault extends WeatherBotFacade implements States  {



    //@Override
    public void executeState(Update update, Long chatId, String messageText, String userFirstName) {
        // /help - Список команд
        if (messageText.equals(MainCommand.HELP.name())) {
            sendMessage(update, messageGenerator.generateHelpMessage());
        }

        // /setcity - Установка стандартного города
        else if (messageText.equals(MainCommand.SETCITY.name())) {
            chatConfigService.setBotState(chatId, BotState.SET_CITY);
            sendMessage(update, "Введите новый стандартный город");
        }

        // /city - Текущий стандартный город для чата
        else if (messageText.equals(MainCommand.CITY.name())) {
            if (chatConfigService.getCity(chatId) != null && !chatConfigService.getCity(chatId).equals("")) {
                sendMessage(update, messageGenerator.generateSuccessGetCity(chatConfigService.getCity(chatId)));
            } else{ sendMessage(update, messageGenerator.generateErrorGetCity());}
        }

        // /now - Узнать текущую погоду
        else if (messageText.equals(MainCommand.NOW.name())) {
            chatConfigService.setBotState(chatId, BotState.NOW);
            sendMessage(update, "Выберите город", KeyboardType.CITY_CHOOSE);
        }


    }
}
