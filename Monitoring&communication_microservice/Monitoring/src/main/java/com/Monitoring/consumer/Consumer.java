package com.Monitoring.consumer;

import com.Monitoring.Entity.Device;
import com.Monitoring.Entity.Message;
import com.Monitoring.Entity.Sensor;
import com.Monitoring.Entity.Measurement;
import com.Monitoring.Repository.DeviceRepository;
import com.Monitoring.Repository.MonitoringRepository;
import com.Monitoring.Service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);


    private final MonitoringRepository repository;
    private final DeviceRepository deviceRepository;
    private final MessageService messageService;

    private int counter = 0;
    List<Double> values = new ArrayList<>();

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(Measurement measurement) {
        LOGGER.info(String.format("Received JSON message -> %s", measurement.toString()));

        System.out.println("counter:" + counter);
        if (counter < 6) {

            values.add(measurement.getMeasurementValue());
            System.out.println(measurement.getMeasurementValue());
            counter++;
        }

        if(counter == 6){

            System.out.println(values + "\n");
            double totalEnergyConsumption = calculateTotalEnergyConsumption(values);
            saveToSensorTable(measurement.getDeviceID(), measurement.getTimestamp(), measurement.getMeasurementValue(), totalEnergyConsumption);

            counter = 0;
           values.clear();
        }
    }

    @RabbitListener(queues = {"${rabbitmq.queue2.name}"})
    public void consumeMessage(Device device) {
        LOGGER.info(String.format("Received message -> %s", device.toString()));

        deviceRepository.save(device);
    }


    private double calculateTotalEnergyConsumption(List<Double> measurementValues) {

        double totalEnergyConsumption = 0;

        for(Double value: measurementValues){
            totalEnergyConsumption += value;
        }

        LOGGER.info("Total energy consumption " + totalEnergyConsumption);

        return totalEnergyConsumption;

    }

    private void saveToSensorTable(Long deviceId, Long timestamp, Double measurementValue, Double totalEnergyConsumption) {

        Device device = deviceRepository.findDeviceById(deviceId);

        System.out.println("\n\n" + device.toString() + "\n\n");

        if(totalEnergyConsumption > device.getMaxHEnergyConsuption()){
            messageService.sendMessage(Message
                            .builder()
                            .idClient(device.getIdClient())
                            .idDevice(device.getId())
                            .build());
        }


        Sensor sensor = Sensor.builder()
                .device(device)
                .idClient(device.getIdClient())
                .timestamp(timestamp)
                .measurementValue(measurementValue)
                .totalEnergyConsumption(totalEnergyConsumption)
                .build();

        repository.save(sensor);
    }
}
