package com.dominik.klientbackend.controller;



import data.CarData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/carData")
public class CarDataController {

    @GetMapping
    public Map<String, Map<String, List<String>>> getCarData() {
        return CarData.getAll();
    }
}
