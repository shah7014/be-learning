package com.example.bookportal.service;

import com.example.bookportal.dto.request.ZipcodeRequest;
import com.example.bookportal.entity.City;
import com.example.bookportal.entity.Zipcode;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.repository.ZipcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ZipcodeService {

    private final ZipcodeRepository zipcodeRepository;
    private final CityService cityService;

    public Zipcode addZipcode(ZipcodeRequest zipcodeRequest) throws NotFoundException {
        City city = null;
        if (Objects.nonNull(zipcodeRequest.getCityId())) {
         city = cityService.getCityById(zipcodeRequest.getCityId());
        }
        Zipcode zipcode = ZipcodeRequest.getAsZipCode(zipcodeRequest, city);
        return zipcodeRepository.save(zipcode);
    }

    public Zipcode getZipCode(Long zipcodeId) throws NotFoundException {
        return zipcodeRepository.findById(zipcodeId)
                .orElseThrow(() -> new NotFoundException("Zipcode with id: " + zipcodeId + " not found"));
    }

    public List<Zipcode> getZipcodes() {
        return zipcodeRepository.findAll();
    }

    public Zipcode deleteZipcode(Long zipcodeId) throws NotFoundException {
        Zipcode zipcodeToBeDeleted = getZipCode(zipcodeId);
        zipcodeRepository.delete(zipcodeToBeDeleted);
        return zipcodeToBeDeleted;
    }

    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequest zipcodeRequest) throws NotFoundException {
        Zipcode zipcodeToBeEdited = getZipCode(zipcodeId);
        zipcodeToBeEdited.setCode(zipcodeRequest.getZipcode());
        if(Objects.nonNull(zipcodeRequest.getCityId())) {
            City city = cityService.getCityById(zipcodeRequest.getCityId());
            zipcodeToBeEdited.setCity(city);
        }
        return zipcodeRepository.save(zipcodeToBeEdited);
    }

    public Zipcode createZipcodeAndCity(ZipcodeRequest zipcodeRequest) {
        if (Objects.isNull(zipcodeRequest.getCityName())) {
            throw new IllegalArgumentException("New city name must be defined");
        }
        City city = City.builder()
                .name(zipcodeRequest.getCityName())
                .build();
        Zipcode zipcode = Zipcode.builder()
                .city(city)
                .code(zipcodeRequest.getZipcode())
                .build();
        return zipcodeRepository.save(zipcode);
    }
}
