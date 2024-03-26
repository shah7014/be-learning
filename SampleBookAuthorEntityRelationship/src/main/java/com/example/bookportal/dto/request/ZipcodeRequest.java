package com.example.bookportal.dto.request;

import com.example.bookportal.entity.City;
import com.example.bookportal.entity.Zipcode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZipcodeRequest {
    @NotNull(message = "Zip Code cannot be null")
    @NotBlank(message = "Zip Code cannot be blank")
    private String zipcode;

    private Long cityId;

    private String cityName;

    public static Zipcode getAsZipCode(ZipcodeRequest zipcodeRequest, City city) {
        return Zipcode.builder()
                .code(zipcodeRequest.getZipcode())
                .city(city)
                .build();
    }
}
