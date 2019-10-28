package com.eyetanfelix.services;

import com.eyetanfelix.model.Forecast;
import com.eyetanfelix.model.Location;
import com.eyetanfelix.model.WeatherRequest;
import com.eyetanfelix.model.WeatherResponse;

public interface WeatherService {
    WeatherResponse fetchData(WeatherRequest weatherRequest);
    Location extractCoordinates(String response) throws Exception;
    Location location(WeatherRequest weatherRequest);
    Forecast weatherForecast(Location coordinates);
}
