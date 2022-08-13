package com.example.demo.Service.Weather;

import com.example.demo.Config.JSON.JSONJacksonWeather.WeatherNow;
import com.example.demo.Config.JSON.JSONJcksonForecastWeather.Forecast;
import com.example.demo.Service.Bot.BotConfigService;
import com.example.demo.Service.ForecastWeather.ForecastWeatherService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс описывает генератор сообщений используемый ботом для общения с пользователем
 */
@Service
public class MessageGeneratorService {
    @Autowired
    private BotConfigService botConfigService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ForecastWeatherService forecastWeatherService;


    private String message;

    public String generateStartMessage(String name) {
        return EmojiParser.parseToUnicode("Привет, " + name + " :wave: \n Я погодный бот, умею показывать погоду в любом городе мира, \n Чтобы узнать, как мной пользоваться - введи /help");
    }

    public String generateHelpMessage() {
        message = "";
        message = ":sunny: Вот мои доступные команды ";
        //System.out.println(message + botConfigService.getAllCommands());

        return EmojiParser.parseToUnicode(message + botConfigService.getAllCommands());
    }

    public String generateSuccessCancel() {
        return EmojiParser.parseToUnicode(":white_check_mark: Активная команда успешно отклонена");
    }

    public String generateSuccessSetCity(String city) {
        return EmojiParser.parseToUnicode(":white_check_mark: Новый стандартный город - " + city);
    }

    public String generateErrorCity() {
        return EmojiParser.parseToUnicode(":x: Такого города не существует");
    }

    public String generateSuccessGetCity(String city) {
        return EmojiParser.parseToUnicode(":cityscape: Стандартный город - " + city);
    }

    public String generateErrorGetCity() {
        return EmojiParser.parseToUnicode(":x: Стандартный город не назначен");
    }

    public String generateCurrentWeather(String city) {                      // использует weatherService для создания запроса на погодный API
        WeatherNow weatherNow = weatherService.getCurrentWeather(city);
        int mm = (int) (weatherNow.getMain().getPressure() * 0.750062D);
        return EmojiParser.parseToUnicode("Текущая погода\n\n" +
                "В городе " + city + " " + weatherNow.getWeather().get(0).getDescription() + "\n" +
                ":thermometer: Температура: " + weatherNow.getMain().getTemp() + "°C, ощущается как " + weatherNow.getMain().getFeelsLike() + "°C \n" +
                "\uD83D\uDD2E давление " + mm + " мм \n"
                //"\uD83D\uDD2E давление "+ (weatherNow.getMain().getPressure())*0.750062D) + " мм \n"
                + "\uD83D\uDCA8 скорость ветра  " + weatherNow.getWind().getSpeed() + " м/с\n"
                + "\uD83D\uDCA8 порывы ветра " + weatherNow.getWind().getGust() + " м/с\n"
                + "\uD83E\uDDED направление ветра " + weatherNow.getWind().getDeg() + "°\n"
                + "\uD83D\uDCA6 влажность " + weatherNow.getMain().getHumidity() + " %");
    }


    public String regEx(String reg) {
        return reg.replaceAll("(.)*T", "");
    }


    /**
     * Метод использует
     */
    public String generateForecastWeather(String city) {

        Forecast forecast = forecastWeatherService.getWeatherForecastCity(city);


        List<String> collections = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(forecast.getHourly().getTime()[0]);
        stringBuilder.append("\n\n");

        String windSpeed;

        for (int i = 0; i < 24; ) {

            int mm = (int) (forecast.getHourly().getSurface_pressure()[i] * 0.750062D);
            windSpeed = (forecast.getHourly().getWindspeed_10m()[i]);

            stringBuilder.append(forecast.getHourly().getTime()[i].replaceAll("(.)*T", "") + "           " + forecast.getHourly().getTemperature_2m()[i] + "°           "
                    + forecast.getHourly().getPrecipitation()[i] +
                    // "      " +windSpeed+ "     "+ mm + "     " + forecast.getHourly().getWinddirection_10m()[i].replaceAll("\\.(.)*$","")+"°"+
                    "\n\n");
            i = i + 4;
        }


        stringBuilder.append(forecast.getHourly().getTime()[24]);
        stringBuilder.append("\n");
        stringBuilder.append("  ⏱️                :thermometer:               ☔ \n");


        for (int i = 24; i < 49; ) {

            int mm = (int) (forecast.getHourly().getSurface_pressure()[i] * 0.750062D);
            windSpeed = (forecast.getHourly().getWindspeed_10m()[i]);

            stringBuilder.append(forecast.getHourly().getTime()[i].replaceAll("(.)*T", "") + "           " + forecast.getHourly().getTemperature_2m()[i] + "°           "
                    + forecast.getHourly().getPrecipitation()[i] +
                    // "      " +windSpeed+ "     "+ mm + "     " + forecast.getHourly().getWinddirection_10m()[i].replaceAll("\\.(.)*$","")+"°"+
                    "\n\n");
            i = i + 4;
        }




        return EmojiParser.parseToUnicode("    ⏱️                :thermometer:               ☔ \n" +
                //    "    \uD83D\uDD2E      \uD83E\uDDED \n
                stringBuilder.toString());


    }

}