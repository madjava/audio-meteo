package com.eyetanfelix.controllers;

import com.eyetanfelix.exceptions.WeatherForecastException;
import com.eyetanfelix.model.WeatherRequest;
import com.eyetanfelix.model.WeatherResponse;
import com.eyetanfelix.services.WeatherServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@Slf4j
public class WeatherController {

    private WeatherServiceImpl weatherService;
    public WeatherController(WeatherServiceImpl weatherService){
        this.weatherService = weatherService;
    }

    @PostMapping("/location")
    public WeatherResponse fetchWeather(@RequestBody WeatherRequest weatherRequest) throws WeatherForecastException {
        if (null == weatherRequest.getLocation() || weatherRequest.getLocation().isEmpty()) {
            throw new WeatherForecastException("Please provide a location");
        }
        return weatherService.fetchData(weatherRequest);
    }
}
