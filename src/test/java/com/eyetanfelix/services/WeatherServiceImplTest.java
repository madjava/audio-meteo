package com.eyetanfelix.services;

import com.eyetanfelix.model.Location;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class WeatherServiceImplTest {

//    @MockBean
//    private RestTemplate weatherRestTemplate;
//
//    @InjectMocks
//    private WeatherServiceImpl weatherService;
//
//    @Test
//    public void test_extractCoordinates_with_valid_response() throws Exception {
//        String response = "{\"features\":[{\"geometry\":\"type\":\"Point\",\"coordinates\":[20,-0.1]}]}";
//
//        Location location = weatherService.extractCoordinates(response);
//
//        assertEquals("Should have correct latitude set",location.getLatitude(), "-0.1");
//
//    }
}