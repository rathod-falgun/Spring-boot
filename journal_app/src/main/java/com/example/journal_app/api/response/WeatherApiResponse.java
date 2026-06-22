package com.example.journal_app.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {
    private Current current;
    private String origin;
    @Getter
    @Setter
    public class Current {

        @JsonProperty("observation_time") // this is used because if we want to give some other name to responsed
        // variable so how to make a relation between our variable and responsed
        // variable
        private String observationTime;
        private int temperature;
        private List<String> weather_descriptions;
    }

}