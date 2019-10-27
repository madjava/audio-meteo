package com.eyetanfelix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "My Weather App");
        model.addAttribute("desc", "Tell me the weather");
        return "index";
    }
}
