package com.eyetanfelix.controllers;

import com.eyetanfelix.model.WeatherRequest;
import com.eyetanfelix.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "My Weather App");
        model.addAttribute("desc", "Tell me the weather");
        return "index";
    }

}
