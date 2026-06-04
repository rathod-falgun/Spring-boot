package com.example.journal_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.journal_app.api.response.WeatherApiResponse;

@Component
public class WeatherService {
    private static final String apiKey = "3ae8d66b9e083e4dad4f6e89b5742ff3";
    private static final String API = "https://api.weatherstack.com/current?access_key={apiKey}&query={city}";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherApiResponse getWeather(String city) {
        String finalApi = API.replace("{city}", city).replace("{apiKey}", apiKey);
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null,
                WeatherApiResponse.class);

        WeatherApiResponse body = response.getBody();
        return body;
    }
}
