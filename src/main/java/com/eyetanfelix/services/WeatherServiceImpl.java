package com.eyetanfelix.services;

import com.eyetanfelix.exceptions.WeatherForecastException;
import com.eyetanfelix.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//  const url = `https://api.darksky.net/forecast/329a6cebd00eebb1d8a45e976d8d75a2/${lat},${lng}?units=si`;
// const url = `https://api.mapbox.com/geocoding/v5/mapbox.places/${encodeURIComponent(address)}.json?access_token=pk.eyJ1IjoibWFkamF2YSIsImEiOiJjajFtMDdzMjIwMDB2MnFwZjN6c2lsb21wIn0.T85R6XMTQPLwOA_LcMLRyQ&limit=1`;
@Service
public class WeatherServiceImpl implements WeatherService {
    public static final int HOURS = 24;
    @Value("${mapbox.api}")
    private String mapBoxApi;

    @Value("${mapbox.token}")
    private String mapBoxToken;

    @Value("${darksky.api}")
    private String darkSkyAPi;

    @Value("${darksky.token}")
    private String darkSkyToken;

    private RestTemplate weatherRestTemplate;

    public WeatherServiceImpl(RestTemplate weatherRestTemplate) {
        this.weatherRestTemplate = weatherRestTemplate;
    }

    public WeatherResponse fetchData(WeatherRequest weatherRequest) throws WeatherForecastException {
        WeatherResponse response = WeatherResponse.builder()
                .message(String.format("No forecast found for %s", weatherRequest.getLocation()))
                .build();

        Location coordinates = location(weatherRequest);
        if (null == coordinates) {
            response.setMessage(String.format("No data found for location: %s", weatherRequest.getLocation()));
            return response;
        }
        Forecast forecast = weatherForecast(coordinates);
        if (null != forecast) {
            response.setForecast(forecast);
        }
        return response;
    }

    public Forecast weatherForecast(Location coordinates) throws WeatherForecastException {
        String lat = coordinates.getLatitude();
        String lng = coordinates.getLongitude();
        String darkSkyUrl = "https://api.darksky.net/forecast/" + darkSkyToken + "/" + lat + "," + lng + "?units=si&exclude=flags,minutely,daily,offset,alerts";
        ResponseEntity<Forecast> response = weatherRestTemplate.getForEntity(darkSkyUrl, Forecast.class);
        Forecast forecast = response.getBody();
        if (null == forecast) {
            throw new WeatherForecastException("No forecast available");
        }
        return forecast;
    }

    public Location extractCoordinates(String response) throws JsonProcessingException {
        Location location = null;
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        JsonNode featuresArray = root.get("features");
        JsonNode feature = featuresArray.get(0);

        if (null != feature) {
            JsonNode geometry = feature.get("geometry");
            JsonNode coordinates = geometry.get("coordinates");
            location = Location.builder()
                    .latitude(coordinates.get(1).asText())
                    .longitude(coordinates.get(0).asText())
                    .build();
        }

        return location;
    }

    public Location location(WeatherRequest weatherRequest) {
        String location = URLEncoder.encode(weatherRequest.getLocation(), StandardCharsets.UTF_8);
        String mapBoxUrl = mapBoxApi + "/geocoding/v5/mapbox.places/" + location + ".json?access_token=" + mapBoxToken + "&limit=1";

        ResponseEntity<String> response = weatherRestTemplate.getForEntity(mapBoxUrl, String.class);
        if (null == response) {
            throw new WeatherForecastException(String.format("No location data found for %s", weatherRequest.getLocation()));
        }

        Location coordinates = null;
        try {
            coordinates = extractCoordinates(response.getBody());
        } catch (JsonProcessingException ex) {
            throw new WeatherForecastException(ex.getMessage());
        }
        return coordinates;
    }

    private Date addHoursDate(int hours) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return new Date(calendar.getTimeInMillis());
    }

}
