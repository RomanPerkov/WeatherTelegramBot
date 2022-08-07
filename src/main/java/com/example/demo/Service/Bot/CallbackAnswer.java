package com.example.demo.Service.Bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * Класс отвечает за то, что бы нажатая кнопка не была в состоянии загрузки после нажатия
 */
@Service
public class CallbackAnswer {
    @Autowired
    private BotConfigService botConfigService;

    public void callbackAnswer(String callbackId) throws IOException, InterruptedException {
        HttpClient telegramApiClient = HttpClient.newHttpClient();
        HttpRequest telegramCallbackAnswerReq = HttpRequest.newBuilder(URI
                .create(botConfigService
                        .getTelegramCallbackAnswerTemp()
                        .replace("POIUPOIUPOIU",botConfigService.getBotAccessToken())
                        .replace("MNBVCMNBVC",callbackId)))
                .GET().build();
        System.out.println(telegramCallbackAnswerReq);

        telegramApiClient.send(telegramCallbackAnswerReq, HttpResponse.BodyHandlers
                .ofString());
    }
}