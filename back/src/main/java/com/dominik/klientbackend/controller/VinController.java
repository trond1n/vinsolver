package com.dominik.klientbackend.controller;

import com.dominik.klientbackend.service.VinSolver;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vin")
public class VinController {

    @GetMapping("/{vin}")
    public VinSolver getCarInfo(@PathVariable String vin) {
        return new VinSolver(vin); // tworzy obiekt z analizą VIN
    }
}
