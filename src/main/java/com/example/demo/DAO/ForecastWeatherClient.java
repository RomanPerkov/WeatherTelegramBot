package com.example.demo.DAO;

import com.example.demo.Config.JSON.JSONJcksonForecastWeather.Forecast;
import com.example.demo.Service.Bot.BotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ForecastWeatherClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BotConfigService botConfigService;



    public Forecast getForecastWeatherCity(String lat, String lon) {


        try {
            Forecast forecast = restTemplate.getForObject(botConfigService.getForecastWeatherApi()
                            .replace("LATITUDE", lat)
                            .replace("LONTITUDE",lon),
                    Forecast.class);


            return forecast;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
