package com.Monitoring.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Measurement  {

    private Long deviceID;
    private Long timestamp;
    private Double measurementValue;

}