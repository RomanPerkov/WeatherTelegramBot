package com.example.demo.JSONJacksonWeather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WeatherNow {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
}