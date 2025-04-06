package com.Monitoring.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chart {

    public long idClient;
    private Map<Integer, Double> consumptionPerHour;


}
