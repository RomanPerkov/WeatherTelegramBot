package com.example.demo.DAO;

import com.example.demo.Config.JSON.JSONJacksonGeo.Cordinates;
import com.example.demo.Service.Bot.BotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class GeocodingClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BotConfigService botConfigService;


    public Cordinates[] getGeoCurrentCity(String city) {


        try {
            Cordinates[] cordinates = restTemplate.getForObject(botConfigService.getGeoCoding()
                            .replace("QWERTYQWERTY", city),
                    Cordinates[].class);
            System.out.println(cordinates);
            return cordinates;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> generateGeoCordinates(Cordinates[] cordinates) {
        List<String> tempList = new ArrayList<>();
        tempList.add(cordinates[0].getLat());
        tempList.add(cordinates[0].getLon());
        //System.out.println("lat"+cordinates[0].getLat()+"lon"+cordinates[0].getLon());
        return tempList;
    }
}

