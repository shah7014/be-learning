package com.example.bookportal.controller;

import com.example.bookportal.dto.request.ZipcodeRequest;
import com.example.bookportal.entity.Zipcode;
import com.example.bookportal.exception.NotFoundException;
import com.example.bookportal.service.ZipcodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
@RequiredArgsConstructor
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @PostMapping
    public ResponseEntity<Zipcode> addZipcode(@RequestBody @Valid ZipcodeRequest zipcodeRequest) throws NotFoundException {
        Zipcode newZipcode = zipcodeService.addZipcode(zipcodeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(newZipcode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zipcode> getZipCode(@PathVariable(name = "id", required = true) Long zipcodeId) throws NotFoundException {
        Zipcode zipcode = zipcodeService.getZipCode(zipcodeId);
        return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Zipcode>> getZipcodes() {
        List<Zipcode> zipcodes = zipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes, HttpStatus.OK);
    }

//    REM: cascading applied so deleting a zipcode will delete the city associated with it
    @DeleteMapping("/{id}")
    public ResponseEntity<Zipcode> deleteZipcode(@PathVariable(name="id", required = true) Long zipcodeId) throws NotFoundException {
        Zipcode zipcodeDeleted = zipcodeService.deleteZipcode(zipcodeId);
        return ResponseEntity.status(HttpStatus.OK).body(zipcodeDeleted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zipcode> editZipcode(@PathVariable(name = "id", required = true)Long zipcodeId,
                                               @RequestBody @Valid ZipcodeRequest zipcodeRequest) throws NotFoundException {
        Zipcode editedZipcode = zipcodeService.editZipcode(zipcodeId, zipcodeRequest);
        return new ResponseEntity<>(editedZipcode, HttpStatus.OK);
    }

//    REM: as cascade on zipcode is ste to all, so saving a zipcode automatically saves a new city also
    @PostMapping("/createZipcodeAndCity")
    public ResponseEntity<Zipcode> createZipcodeAndCity(@RequestBody @Valid ZipcodeRequest zipcodeRequest) {
        Zipcode zipcode = zipcodeService.createZipcodeAndCity(zipcodeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(zipcode);
    }
}
