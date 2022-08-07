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


    @SneakyThrows //отслеживание Exceptions
    @Override
    public void onUpdateReceived(Update update) {
        weatherBotFacade.handleUpdate(update);
    }

//    private Long setChatIdToMessageBuilder(Update update, SendMessage.SendMessageBuilder messageBuilder) {
//        Long chatId = null;
//        if (update.hasMessage()) {
//            chatId = update.getMessage().getChatId();
//            messageBuilder.chatId(update.getMessage().getChatId().toString());
//        } else if (update.hasChannelPost()) {
//            chatId = update.getChannelPost().getChatId();
//            messageBuilder.chatId(update.getChannelPost().getChatId().toString());
//        } else if (update.hasCallbackQuery()) {
//            chatId = update.getCallbackQuery().getMessage().getChatId();
//            messageBuilder.chatId(update.getCallbackQuery().getMessage().getChatId().toString());
//        }
//        return chatId;
//    }
//
//    public void sendMessage(Update update, String messageText) {
//        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
//
//        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);
//
//        messageBuilder.text(messageText);
//
//        try {
//            weatherBot.execute(messageBuilder.build());
//        } catch (TelegramApiException telegramApiException) {
//            telegramApiException.printStackTrace();
//        }
//    }
//
//    public void sendMessage(Update update, String messageText, KeyboardType keyboardType) {
//        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
//
//        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);
//
//        messageBuilder.text(messageText);
//
//        switch (keyboardType) {
//            case CITY_CHOOSE: {
//                //устанавливаем кнопки, созданные выше
//                messageBuilder.replyMarkup(keyboardService.setChooseCityKeyboard(chatId));
//                break;
//            }
//        }
//
//        try {
//            weatherBot.execute(messageBuilder.build());
//        } catch (TelegramApiException telegramApiException) {
//            telegramApiException.printStackTrace();
//        }
//    }
//
//
//    private void handleBotState(Update update, Long chatId, String messageText, String userFirstName) throws IOException {
//
//
//        BotState botState = chatConfigService.getBotState(chatId);
//
//
//        if (messageText.equals(MainCommand.START.name())) {
//            chatConfigService.setBotState(chatId, BotState.DEFAULT);
//            sendMessage(update, messageGenerator.generateStartMessage(userFirstName));
//
//        }
//
//        // /cancel Возвращение бота в состояние DEFAULT (отмена текущей команды)
//        if (messageText.equals(MainCommand.CANCEL.name())) {
//            if (botState == BotState.DEFAULT) {
//                sendMessage(update, "Нет активной команды для отклонения");
//            } else {
//                chatConfigService.setBotState(chatId, BotState.DEFAULT);
//                sendMessage(update, messageGenerator.generateSuccessCancel());
//
//            }
//        }
//        switch (botState) {
//            case DEFAULT:
//                executeStateDefault(update, chatId, messageText);
//
//
//                break;
//
//
//            case SET_CITY:
//                executeStateSetCity(update, chatId, messageText);
//
//
//                break;
//
//
//            case NOW:
//                executeStateNow(update, chatId, messageText);
//
//
//                break;
//
//
//            case SEARCH_NOW:
//                executeStateSearchNow(update, chatId, messageText);
//
//
//                break;
//
//        }
//
//
//    }
//
//
//    /**
//     * Далее описываются состояния бота
//     *
//     * @param update
//     * @param chatId
//     * @param messageText
//     */
//    private void executeStateDefault(Update update, Long chatId, String messageText) {
//        // /help - Список команд
//        if (messageText.equals(MainCommand.HELP.name())) {
//            sendMessage(update, messageGenerator.generateHelpMessage());
//        }
//
//        // /setcity - Установка стандартного города
//        else if (messageText.equals(MainCommand.SETCITY.name())) {
//            chatConfigService.setBotState(chatId, BotState.SET_CITY);
//            sendMessage(update, "Введите новый стандартный город");
//        }
//
//        // /city - Текущий стандартный город для чата
//        else if (messageText.equals(MainCommand.CITY.name())) {
//            if (chatConfigService.getCity(chatId) != null && !chatConfigService.getCity(chatId).equals("")) {
//                sendMessage(update, messageGenerator.generateSuccessGetCity(chatConfigService.getCity(chatId)));
//            } else sendMessage(update, messageGenerator.generateErrorGetCity());
//        }
//
//        // /now - Узнать текущую погоду
//        else if (messageText.equals(MainCommand.NOW.name())) {
//            chatConfigService.setBotState(chatId, BotState.NOW);
//            sendMessage(update, "Выберите город", KeyboardType.CITY_CHOOSE);
//        }
//    }
//
//    private void executeStateNow(Update update, Long chatId, String messageText) {
//        // если выбран не стандартный город
//        if (messageText.equals(keyboardService.getChooseCityNowButtonData().toUpperCase())) {
//            chatConfigService.setBotState(chatId, BotState.SEARCH_NOW);
//        }
//
//        // погода для стандартного города
//        else {
//            chatConfigService.setBotState(chatId, BotState.DEFAULT);
//            sendMessage(update, messageGenerator.generateCurrentWeather(chatConfigService.getCity(chatId)));
//        }
//    }
//
//    @SneakyThrows
//    private void executeStateSetCity(Update update, Long chatId, String messageText) {
//        // проверка - существует ли введенный пользователем город
//        System.out.println("9999999999999");
//        if (weatherService.isCity(messageText.toLowerCase(Locale.ROOT))) {
//            System.out.println(messageText);
//            System.out.println("2222222222");
//            chatConfigService.setCity(chatId, messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT));
//            System.out.println(messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT));
//            chatConfigService.setBotState(chatId, BotState.DEFAULT);
//            sendMessage(update, messageGenerator.generateSuccessSetCity(chatConfigService.getCity(chatId)));
//        } else sendMessage(update, messageGenerator.generateErrorCity());
//    }
//
//    @SneakyThrows
//    private void executeStateSearchNow(Update update, Long chatId, String messageText) {
//        //проверка на существование города
//        if (!weatherService.isCity(messageText)) {
//            sendMessage(update, messageGenerator.generateErrorCity());
//        }
//
//        // погода для введенного города
//        else {
//            sendMessage(update, messageGenerator.generateCurrentWeather(messageText.charAt(0) + messageText.substring(1).toLowerCase(Locale.ROOT)));
//            chatConfigService.setBotState(chatId, BotState.DEFAULT);
//        }
//    }
}