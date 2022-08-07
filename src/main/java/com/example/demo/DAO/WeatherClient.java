package com.example.demo.DAO;

import com.example.demo.JSONJacksonWeather.WeatherNow;
import com.example.demo.Service.Bot.BotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс описывает реализацию обращения к погодному API
 */

@Component//////////////////////////////////////////
public class WeatherClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BotConfigService botConfigService;

    //получение текущей погоды
    public WeatherNow getNowWeather(String city){
        try {
            WeatherNow weatherNow = restTemplate.getForObject(botConfigService.getNowApiTemp()
                            .replace("QWERTYQWERTY",city),
                    WeatherNow.class);
            System.out.println(weatherNow);
            return weatherNow;

//            return restTemplate.getForObject(botConfigService.getNowApiTemp()
//                            .replace("{qqq}",city),
//                    WeatherNow.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //проверка существования города
    public boolean isCity(String city) throws IOException {

        URL weatherApiUrl = new URL(botConfigService.getNowApiTemp().replace("QWERTYQWERTY",city));

        HttpURLConnection weatherApiConnection = (HttpURLConnection)weatherApiUrl.openConnection();
        weatherApiConnection.setRequestMethod("GET");
        weatherApiConnection.connect();
        return weatherApiConnection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }
}