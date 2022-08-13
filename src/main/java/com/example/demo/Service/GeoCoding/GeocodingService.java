package com.example.demo.Service.GeoCoding;

import com.example.demo.DAO.GeocodingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeocodingService {
    @Autowired
    GeocodingClient geocodingClient;

    public List<String> getCurrentCityCordinate(String city){
        return geocodingClient.generateGeoCordinates(geocodingClient.getGeoCurrentCity(city));
    }
}
