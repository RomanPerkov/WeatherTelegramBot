package com.example.demo.Service.Weather;

import com.example.demo.JSONJacksonWeather.WeatherNow;
import com.example.demo.DAO.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Класс описывает сервис обращения к погодному API
 */

@Service
public class WeatherService {
    @Autowired
    private WeatherClient weatherClient;

    public boolean isCity(String city) throws IOException {
        return weatherClient.isCity(city);
    }

    public WeatherNow getCurrentWeather(String city){
        return weatherClient.getNowWeather(city);
    }
}