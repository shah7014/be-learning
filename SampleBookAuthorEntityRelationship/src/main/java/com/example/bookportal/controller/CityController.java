package com.example.bookportal.controller;

import com.example.bookportal.dto.request.CityRequest;
import com.example.bookportal.entity.City;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody @Valid CityRequest cityRequest) {
        City city = cityService.addCity(cityRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable(name = "id", required = true) Long cityId) throws NotFoundException {
        City city = cityService.getCityById(cityId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

//    REM: we can't delete a city that already has a zipcode associated,
//    as zipcode column contains cityId has foreign key
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable(name = "id", required = true) Long cityId) throws NotFoundException {
        City city = cityService.deleteCity(cityId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> editCity(@PathVariable(name = "id", required = true) Long cityId,
                                         @RequestBody @Valid CityRequest cityRequest) throws NotFoundException {
        City city = cityService.editCity(cityId, cityRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }
}
