package com.eyetanfelix.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponse {
    private Forecast forecast;
    private String message;
}
