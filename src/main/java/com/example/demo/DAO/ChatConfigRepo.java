package com.example.demo.DAO;

import com.example.demo.Entity.ChatConfig;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * репозиторий описывающий получения данных
 * используется Spring Data JPA
 */

public interface ChatConfigRepo extends JpaRepository<ChatConfig, Integer> {

    ChatConfig findAllByChatId(Long chatId);
    void deleteByChatId(Long chatId);
}
