package com.example.demo.Service.Bot;


import com.example.demo.Config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * Класс представляет интерфейс для взаимодействия с API телеграм
 * любой сервис для взаимодействия с API делеграм может использовать этот класс
 */
public class BotConfigService {

    @Autowired
    BotConfig botConfig = new BotConfig();

    public String getTelegramCallbackAnswerTemp() {
        return botConfig.getTelegramCallbackAnswerTemp();
    }

    public String getNowApiTemp() {
        return botConfig.getNowWeatherApiTemp();
    }

    public String getAllCommands() {
        return botConfig.getCommands();
    }

    public String getBotUsername() {
        return botConfig.getBotName();
    }

    public String getBotAccessToken() {
        return botConfig.getAccessToken();
    }

}
