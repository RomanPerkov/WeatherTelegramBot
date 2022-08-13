package com.example.demo.Telegram_Bot;

import com.example.demo.Contsants.BotState;
import com.example.demo.Contsants.KeyboardType;
import com.example.demo.Service.Bot.CallbackAnswer;
import com.example.demo.Service.Bot.ChatConfigService;
import com.example.demo.Service.Bot.KeyboardService;
import com.example.demo.Service.Weather.MessageGeneratorService;
import com.example.demo.Service.Weather.WeatherService;
import com.example.demo.Telegram_Bot.States.States;
import com.example.demo.Telegram_Bot.States.StatesFacoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Locale;


/**
 * Класс описывает логику бота
 */
@Component
public class WeatherBotFacade {
    @Autowired
    protected ChatConfigService chatConfigService;
    @Autowired
    protected MessageGeneratorService messageGenerator;
    @Autowired
    protected WeatherService weatherService;
    @Autowired
    protected KeyboardService keyboardService;
    @Autowired
    //  @Lazy
    protected WeatherBot weatherBot;
    @Autowired
    protected CallbackAnswer callbackAnswer;
    @Autowired
    //@Lazy
            StatesFacoty statesFacoty;


    States states;


    /**
     * Этот метод берет на себя логику перехватчика сообщений
     */
    public void handleUpdate(Update update) throws IOException, InterruptedException {
        String messageText;         // объявление переменных
        Long chatId;
        String userFirstName = "";

        //если сообщение пришло в лс боту
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId(); // инициализация переменной
            messageText = update.getMessage().getText().toUpperCase().replace("/", "");// инициализация переменной
            userFirstName = update.getMessage().getChat().getFirstName();// инициализация переменной
          //  System.out.println(update.getMyChatMember().getChat().getLinkedChatId());
        }

        //если пришло сообщение с кнопок, которые мы создавали выше
        /**
         * Если приходит ообщение с кнопкок , то в случае если сообщение пришло с кнопки выбрать другой город , бот
         * переходит в состояние SEARCH NOW и введеный пользователем город отправится в методы запросов погодной апи
         * Если приходит сообщение с кнопки отображающий город по умолчанию то бот переходит в состояние NOW
         */
        else if (update.hasCallbackQuery()) {


            callbackAnswer.callbackAnswer(update.getCallbackQuery().getId());

            chatId = update.getCallbackQuery().getMessage().getChatId();
            messageText = update.getCallbackQuery().getData().toUpperCase();
            sendMessage(update, update.getCallbackQuery().getData());



            BotState botState = chatConfigService.getBotState(chatId);
            if (botState == BotState.NOW||botState== BotState.DEFAULT){

                if (messageText.equals(keyboardService.getChooseCityNowButtonData().toUpperCase(Locale.ROOT))) {//если приходит сообщение с кнопки выбрать другой город,
                    chatConfigService.setBotState(chatId, BotState.SEARCH_NOW);
                    return;

                }

                else if (messageText.equals(keyboardService.getCurrentCityNowButton(chatConfigService.getCity(chatId)).toUpperCase(Locale.ROOT))) {
                    chatConfigService.setBotState(chatId, BotState.NOW);

                }

            }//else
                //{chatConfigService.setBotState(chatId, BotState.NOW);}

            if (botState == BotState.FORECAST||botState== BotState.DEFAULT) {
                if (messageText.equals(keyboardService.getChooseCityNowButtonData().toUpperCase(Locale.ROOT))) {
                    chatConfigService.setBotState(chatId, BotState.FORECAST_NOW);
                    return;
                }
                else if (messageText.equals(keyboardService.getCurrentCityNowButton(chatConfigService.getCity(chatId)).toUpperCase(Locale.ROOT))) {
                    chatConfigService.setBotState(chatId, BotState.FORECAST);

                }
            }else{
                //chatConfigService.setBotState(chatId, BotState.FORECAST);
            }
        }

        //если человек присоединился к чату или покинул его
        else if (update.hasMyChatMember()) {
            //удаляем данные о чате из бд, если пользователь покинул чат с ботом
            if (update.getMyChatMember().getNewChatMember().getStatus().equals("kicked")) {
                System.out.println("DELETE");
                chatConfigService.deleteChat(update.getMyChatMember().getChat().getId());
            }

            return;
        } else {

            return;
        }

        //создаём запись о чате в бд и возвращаем приветствие
        if (!chatConfigService.isChatInit(chatId)) {//если чата не существует
            chatConfigService.initChat(chatId); // создаем новый чат, ставим дефолтное состояние
            sendMessage(update, messageGenerator.generateStartMessage(userFirstName));
            onceSend(update);
        } else {
            //отслеживаем состояние бота относительно текущего чата
            handleBotState(update, chatId, messageText, userFirstName);
        }
    }


    private Long setChatIdToMessageBuilder(Update update, SendMessage.SendMessageBuilder messageBuilder) {
        Long chatId = null;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            messageBuilder.chatId(update.getMessage().getChatId().toString());
        } else if (update.hasChannelPost()) {
            chatId = update.getChannelPost().getChatId();
            messageBuilder.chatId(update.getChannelPost().getChatId().toString());
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            messageBuilder.chatId(update.getCallbackQuery().getMessage().getChatId().toString());
        }
        return chatId;
    }

    public void onceSend(Update update) {
        try {
            SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
            String userFirstName = update.getMessage().getChat().getFirstName();
            String description = update.getMessage().getChat().getDescription();
            String bio = update.getMessage().getChat().getBio();
            String lastName = update.getMessage().getChat().getLastName();
            String inviteLink = update.getMessage().getChat().getInviteLink();
            String stickerSetName = update.getMessage().getChat().getStickerSetName();
            String title = update.getMessage().getChat().getTitle();
            String type = update.getMessage().getChat().getType();
            messageBuilder.text("Новый пользователь " + userFirstName + "\n" +
                    "LastName " + lastName + "\n"
                    + "inviteLink " + inviteLink + "\n"
                    + " stickerSetName " + stickerSetName + "\n"
                    + " title " + title + "\n"
                    + "type " + type + "\n "
                    + "bio " + bio + "\n"
                    + "description " + description).chatId("471234692").build();


            weatherBot.execute(messageBuilder.build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Update update, String messageText) {
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();

        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);

        messageBuilder.text(messageText);

        try {
            weatherBot.execute(messageBuilder.build());
        } catch (TelegramApiException telegramApiException) {
            telegramApiException.printStackTrace();
        }
    }

    public void sendMessage(Update update, String messageText, KeyboardType keyboardType) {
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();

        Long chatId = setChatIdToMessageBuilder(update, messageBuilder);

        messageBuilder.text(messageText);

        switch (keyboardType) {
            case CITY_CHOOSE: {
                //устанавливаем кнопки, созданные выше
                if (chatConfigService.getCity(chatId) != null && !chatConfigService.getCity(chatId).equals("")) {
                    messageBuilder.replyMarkup(keyboardService.setChooseCityKeyboard(chatId));
                } else {
                    sendMessage(update, messageGenerator.generateErrorGetCity());
                    chatConfigService.setBotState(chatId, BotState.SET_CITY);
                }
                break;
            }
        }

        try {
            weatherBot.execute(messageBuilder.build());
        } catch (TelegramApiException telegramApiException) {
            telegramApiException.printStackTrace();
        }
    }




    private void handleBotState(Update update, Long chatId, String messageText, String userFirstName) throws IOException {

        //System.out.println(chatConfigService.getBotState(chatId));


        states = statesFacoty.statesFactory(update, chatId, messageText, userFirstName);
        states.executeState(update, chatId, messageText, userFirstName);


    }
}
