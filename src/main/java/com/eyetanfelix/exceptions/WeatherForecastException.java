package com.eyetanfelix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WeatherForecastException extends RuntimeException {

    public WeatherForecastException() {
        super();
    }

    public WeatherForecastException(String msg) {
        super(msg);
    }

    public WeatherForecastException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WeatherForecastException(Throwable cause) {
        super(cause);
    }
}
