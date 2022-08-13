package com.example.demo.Config.JSON.JSONJcksonForecastWeather;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Hourly {
    private String[] time;
    private String[] temperature_2m;
    private String[] precipitation;
    private String[] windspeed_10m;
    private Integer[] surface_pressure;
    private String[] winddirection_10m;

}
