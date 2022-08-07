package com.example.demo.Service.Weather;

import com.example.demo.JSONJacksonWeather.WeatherNow;
import com.example.demo.Service.Bot.BotConfigService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Класс описывает генератор сообщений используемый ботом для общения с пользователем
 */
@Service
public class MessageGeneratorService {
    @Autowired
    private BotConfigService botConfigService;
    @Autowired
    private WeatherService weatherService;

    private String message;

    public String generateStartMessage(String name){
        return EmojiParser.parseToUnicode("Привет, " + name + " :wave: \nЧтобы узнать, как мной пользоваться - введите /help");
    }

    public String generateHelpMessage(){
        message = "";
        message = ":sunny: Вот мои доступные команды ";
        //System.out.println(message + botConfigService.getAllCommands());

        return EmojiParser.parseToUnicode(message + botConfigService.getAllCommands());
    }

    public String generateSuccessCancel(){
        return EmojiParser.parseToUnicode(":white_check_mark: Активная команда успешно отклонена");
    }

    public String generateSuccessSetCity(String city){
        return EmojiParser.parseToUnicode(":white_check_mark: Новый стандартный город - " + city);
    }

    public String generateErrorCity(){
        return EmojiParser.parseToUnicode(":x: Такого города не существует");
    }

    public String generateSuccessGetCity(String city){
        return EmojiParser.parseToUnicode(":cityscape: Стандартный город - " + city);
    }

    public String generateErrorGetCity(){
        return EmojiParser.parseToUnicode(":x: Стандартный город не назначен");
    }

    public String generateCurrentWeather(String city){                      // использует weatherService для создания запроса на погодный API
        WeatherNow weatherNow = weatherService.getCurrentWeather(city);
        return EmojiParser.parseToUnicode("Текущая погода\n\n" +
                "В городе " + city + " " + weatherNow.getWeather().get(0).getDescription() + "\n" +
                ":thermometer: Температура: " + weatherNow.getMain().getTemp() + "°C, ощущается как " + weatherNow.getMain().getFeelsLike() + "°C \n" +
                "\uD83D\uDD2E давление "+ weatherNow.getMain().getPressure()) + " гПа \n"
                +"\uD83D\uDCA8 скорость ветра  " + weatherNow.getWind().getSpeed() + " м/с\n"
                +"\uD83D\uDCA8 порывы ветра " + weatherNow.getWind().getGust() + " м/с\n"
                +"\uD83E\uDDED направление ветра " + weatherNow.getWind().getDeg()+"°\n"
                +"\uD83D\uDCA6 влажность " + weatherNow.getMain().getHumidity() + " %";
    }
}