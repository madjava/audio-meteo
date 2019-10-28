package com.eyetanfelix.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherRequest {
    private String location;
    private String mobile;
}
