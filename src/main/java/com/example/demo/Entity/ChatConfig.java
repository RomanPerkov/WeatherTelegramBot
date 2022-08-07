package com.example.demo.Entity;

import com.example.demo.Contsants.BotState;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


/**
 * Описание сущности для взаимодействия с БД
 */
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user")
public class ChatConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Long chatId;

    @Column(name = "bot_state")
    private BotState botState;

    @Column(name = "city")
    private String city;


    public ChatConfig(Long chatId, BotState botState) {
        this.chatId = chatId;
        this.botState = botState;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public BotState getBotState() {
        return botState;
    }

    public void setBotState(BotState botState) {
        this.botState = botState;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}