package com.example.demo.Config.JSON.JSONJacksonWeather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/**
 * Класс описывает сущность которая будет создана на основе ответа в формате JSON
 */
public class Main {
    private Integer temp;
    private Double pressure;
    private String humidity;

    @JsonProperty("feels_like")
    private Integer feelsLike;

}
