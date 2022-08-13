package com.example.demo.Telegram_Bot;


import com.example.demo.Service.Bot.BotConfigService;
import com.example.demo.Service.Bot.CallbackAnswer;
import com.example.demo.Service.Bot.ChatConfigService;
import com.example.demo.Service.Bot.KeyboardService;
import com.example.demo.Service.Weather.MessageGeneratorService;
import com.example.demo.Service.Weather.WeatherService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 * Класс описывает бота
 */
@Component
public class WeatherBot extends TelegramLongPollingBot {
    @Autowired
    protected BotConfigService botConfigService;
@Autowired
WeatherBotFacade weatherBotFacade;

    @Autowired
    protected ChatConfigService chatConfigService;
    @Autowired
    protected MessageGeneratorService messageGenerator;
    @Autowired
    protected WeatherService weatherService;
    @Autowired
    protected KeyboardService keyboardService;
    @Autowired
    @Lazy
    protected WeatherBot weatherBot;
    @Autowired
    protected CallbackAnswer callbackAnswer;


    @Override
    public String getBotUsername() {
        return botConfigService.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfigService.getBotAccessToken();
    }


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        weatherBotFacade.handleUpdate(update);
    }


}