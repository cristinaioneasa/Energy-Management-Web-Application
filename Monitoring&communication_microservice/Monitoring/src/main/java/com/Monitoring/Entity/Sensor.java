package com.Monitoring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "idDevice", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_device_sensor"))
    public Device device;
    public Long idClient;
    private Long timestamp;
    private Double measurementValue;
    private Double totalEnergyConsumption;
}
