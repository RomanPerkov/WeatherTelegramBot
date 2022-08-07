package com.example.demo.Config;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class BotConfig {

    private  String accessToken = "TOKEN";
    private  String botName = "UltraGangstaMegaBot";
    private String nowWeatherApiTemp = "http://api.openweathermap.org/data/2.5/weather?q=QWERTYQWERTY&appid=2ca5fe61073486881897b1dd4ac6d004&units=metric&lang=ru";
    private String telegramCallbackAnswerTemp="https://api.telegram.org/botPOIUPOIUPOIU/answerCallbackQuery?callback_query_id=MNBVCMNBVC";
    private String commands;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getNowWeatherApiTemp() {
        return nowWeatherApiTemp;
    }

    public void setNowWeatherApiTemp(String nowWeatherApiTemp) {
        this.nowWeatherApiTemp = nowWeatherApiTemp;
    }

    public String getTelegramCallbackAnswerTemp() {
        return telegramCallbackAnswerTemp;
    }

    public void setTelegramCallbackAnswerTemp(String telegramCallbackAnswerTemp) {
        this.telegramCallbackAnswerTemp = telegramCallbackAnswerTemp;
    }

    public String getCommands() {

        return "\n /help - помощь \n " +
                "/start - дефолтное состояние бота\n" +
                "/now - погода сейчас\n" +
                "/cancel - отмена текущей команды\n" +
                "/setcity - установить город по умолчанию";


    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}


