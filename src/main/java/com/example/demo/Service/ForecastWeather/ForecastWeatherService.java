package com.example.demo.Service.ForecastWeather;


import com.example.demo.Config.JSON.JSONJcksonForecastWeather.Forecast;
import com.example.demo.DAO.ForecastWeatherClient;
import com.example.demo.Service.GeoCoding.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecastWeatherService {
    @Autowired
    ForecastWeatherClient forecastWeatherClient;
    @Autowired
    GeocodingService geocodingService;

    public Forecast getWeatherForecastCity(String city){

        Forecast forecast = forecastWeatherClient.getForecastWeatherCity(geocodingService.getCurrentCityCordinate(city).get(0),geocodingService.getCurrentCityCordinate(city).get(1));
        return  forecast;
    }


}
