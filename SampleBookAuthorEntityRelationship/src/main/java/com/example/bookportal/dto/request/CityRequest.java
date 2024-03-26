package com.example.bookportal.dto.request;

import com.example.bookportal.entity.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {
    @NotBlank(message = "City Name cannot be blank")
    @NotNull(message = "City Name cannot be null")
    private String name;

    public static City getAsCity(CityRequest cityRequest) {
        return City.builder()
                .name(cityRequest.getName())
                .build();
    }
}
