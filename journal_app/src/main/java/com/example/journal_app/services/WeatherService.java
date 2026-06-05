package com.example.journal_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.journal_app.api.response.WeatherApiResponse;

@Component
public class WeatherService {
    private static final String apiKey = "3ae8d66b9e083e4dad4f6e89b5742ff3";
    private static final String GET_API = "https://api.weatherstack.com/current?access_key={apiKey}&query={city}";

    @Autowired
    private RestTemplate restTemplate;

    // To Make GET request to the server of Weatherstack
    public WeatherApiResponse getWeather(String city) {
        String finalApi = GET_API.replace("{city}", city).replace("{apiKey}", apiKey);
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null,
                WeatherApiResponse.class);

        WeatherApiResponse body = response.getBody();
        return body;
    }

    // To Make POST request to the server of Weatherstack but it does not allow to (because of the free trial)
    // make post request within this plan so
    // just demostration of post request

    public WeatherApiResponse setWeather(String city) {

        String req = "username:fr\npassword:fr";

        HttpEntity<String> requestEntity = new HttpEntity<>(req);

        String finalApi = GET_API.replace("{city}", city).replace("{apiKey}", apiKey);

        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, requestEntity,
                WeatherApiResponse.class);

        WeatherApiResponse body = response.getBody();
        return body;
    }
}
