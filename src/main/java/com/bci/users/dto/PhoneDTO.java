package com.bci.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO {
    @JsonProperty("number")
    private long number;
    @JsonProperty("city_code")
    private Integer cityCode;
    @JsonProperty("contry_code")
    private String contryCode;
}
