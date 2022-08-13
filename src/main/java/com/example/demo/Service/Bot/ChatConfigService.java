package com.example.demo.Service.Bot;

import com.example.demo.Contsants.BotState;
import com.example.demo.DAO.ChatConfigRepo;
import com.example.demo.Entity.ChatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
Класс описывает взаимодействие с чатом
 */
@Service
public class ChatConfigService  {

    @Autowired
    private ChatConfigRepo chatConfigRepo;

    public boolean isChatInit(Long chatId){
        return chatConfigRepo.findAllByChatId(chatId) != null;
    }//проверка, есть ли чат с таким id

    //создание нового чата
    public void initChat(Long chatId){
        chatConfigRepo.save(new ChatConfig(chatId, BotState.DEFAULT));
    }

    public void deleteChat(Long chatId){
        chatConfigRepo.deleteByChatId(chatId);
    }

    public void setBotState(Long chatId, BotState botState){             // изменяет состояние бота
        ChatConfig chatConfig = chatConfigRepo.findAllByChatId(chatId);
        chatConfig.setBotState(botState);
        chatConfigRepo.save(chatConfig);
    }

    public BotState getBotState(Long chatId){
        return chatConfigRepo.findAllByChatId(chatId).getBotState();
    }


    public void setCity(Long chatId, String city){
        ChatConfig chatConfig = chatConfigRepo.findAllByChatId(chatId);
        chatConfig.setCity(city);
        chatConfigRepo.save(chatConfig);
    }

    public String getCity(Long chatId){
        return chatConfigRepo.findAllByChatId(chatId).getCity();
    }
}
