package com.example.bookportal.service;

import com.example.bookportal.dto.request.CityRequest;
import com.example.bookportal.entity.City;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    public City addCity(CityRequest cityRequest) {
        City city = CityRequest.getAsCity(cityRequest);
        return cityRepository.save(city);
    }


    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long cityId) throws NotFoundException {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new NotFoundException("City with city id: " + cityId + " does not exist"));
    }

    public City deleteCity(Long cityId) throws NotFoundException {
        City city = getCityById(cityId);
        cityRepository.delete(city);
        return city;
    }

    public City editCity(Long cityId, CityRequest cityRequest) throws NotFoundException {
        City cityToBeEdited = getCityById(cityId);
        cityToBeEdited.setName(cityRequest.getName());
        return cityRepository.save(cityToBeEdited);
    }

    public boolean isCityAlreadyPresent(Long cityId) {
        return cityRepository.existsById(cityId);
    }
}
