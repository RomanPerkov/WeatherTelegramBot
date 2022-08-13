package com.example.demo.Config;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Component
public class BotConfig {

    private String accessToken = "5388414278:AAGudIlxUsпроjjdfDCaqdajjsu5wKNu6eixnt4Opxrqo";
    private String botName = "UltraGangstaMegaBot";
    private String nowWeatherApiTemp = "http://api.openweathermap.org/data/2.5/weather?q=QWERTYQWERTY&appid=2ca5fe61073486881897b1dd4ac6d004&units=metric&lang=ru";
    private String telegramCallbackAnswerTemp = "https://api.telegram.org/botPOIUPOIUPOIU/answerCallbackQuery?callback_query_id=MNBVCMNBVC";
    private String geoCoding = "http://api.openweathermap.org/geo/1.0/direct?q=QWERTYQWERTY&limit=5&appid=2ca5fe61073486881897b1dd4ac6d004";
    private String forecastWeatherApiTemp = "https://api.open-meteo.com/v1/forecast?latitude=LATITUDE8&longitude=LONTITUDE&hourly=temperature_2m,surface_pressure,precipitation,windspeed_10m,winddirection_10m&current_weather=true&timezone=auto";
  // private String forecastWeatherApiTemp = "https://api.open-meteo.com/v1/forecast?latitude=LATITUDE8&longitude=LONTITUDE&hourly=temperature_2m&current_weather=true&timezone=auto";
    private String commands;

    public String getForecastWeatherApiTemp() {
        return forecastWeatherApiTemp;
    }

    public void setForecastWeatherApiTemp(String forecastWeatherApiTemp) {
        this.forecastWeatherApiTemp = forecastWeatherApiTemp;
    }

    public String getGeoCoding() {
        return geoCoding;
    }

    public void setGeoCoding(String geoCoding) {
        this.geoCoding = geoCoding;
    }

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
                "/setcity - установить город по умолчанию\n" +
                "/city - показать текущий город\n" +
                "/forecast - показать прогноз погоды ";



    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}


