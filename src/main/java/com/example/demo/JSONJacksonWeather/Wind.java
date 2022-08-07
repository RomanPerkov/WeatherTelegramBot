package com.example.demo.JSONJacksonWeather;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Wind {
    private String speed;
    private String deg;
    private String gust;
}
