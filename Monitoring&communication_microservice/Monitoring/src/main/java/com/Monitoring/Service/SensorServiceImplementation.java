package com.Monitoring.Service;

import com.Monitoring.Entity.Chart;
import com.Monitoring.Entity.Device;
import com.Monitoring.Entity.Sensor;
import com.Monitoring.Repository.DeviceRepository;
import com.Monitoring.Repository.SensorRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImplementation implements SensorService{


    @Autowired
    private SensorRepository sensorRepository;
    private final JwtService jwtService;

    @Override
    public List<Sensor> findByIdClient(Long idClient) {
        return sensorRepository.findByIdClient(idClient);
    }

    /*
    public List<Sensor> getEnergyConsumptionForDay(Long idClient, LocalDate selectedDay) {

        LocalDateTime startOfDay = selectedDay.atStartOfDay();
        LocalDateTime endOfDay = selectedDay.atTime(23, 59, 59);

        List<Device> devices = deviceRepository.findDeviceByIdClient(idClient);

        List<Long> deviceIds = devices.stream()
                .map(Device::getId)
                .collect(Collectors.toList());

        return sensorRepository.findByDevice_IdInAndTimestampBetweenOrderByTimestampAsc(
                deviceIds, startOfDay.toEpochSecond(ZoneOffset.UTC), endOfDay.toEpochSecond(ZoneOffset.UTC));
    }

     */

    public Chart getSensorsByClient(HttpServletRequest request, Long timestamp, Long idClient) {

        String token = jwtService.extractTokenFromRequest(request);

        if(token == null || token.isEmpty()){
            return null;
        }

        String userRole = jwtService.extractRole(token);
        System.out.println(userRole);
        if(!userRole.equals("CLIENT")) {
            return null;
        }
        List<Sensor> sensors = findByIdClient(idClient);

        // Initialize a map to store total consumption for each hour
        Map<Integer, Double> hoursAndValues = new HashMap<>();
        for (int hour = 0; hour < 24; hour++) {
            hoursAndValues.put(hour, 0.0);
        }

        // Iterate through sensors for the client
        for (Sensor sensor : sensors) {
            // Check if the sensor's timestamp matches the specified day
            Date sensorDate = new Date(sensor.getTimestamp());
            Calendar sensorCalendar = Calendar.getInstance();
            sensorCalendar.setTime(sensorDate);

            // Convert the provided timestamp to a Date object
            Date selectedDate = new Date(timestamp);
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.setTime(selectedDate);

            if (sensorCalendar.get(Calendar.DAY_OF_MONTH) == selectedCalendar.get(Calendar.DAY_OF_MONTH)
                    && sensorCalendar.get(Calendar.MONTH) == selectedCalendar.get(Calendar.MONTH)
                    && sensorCalendar.get(Calendar.YEAR) == selectedCalendar.get(Calendar.YEAR)) {
                // Update the total consumption for the corresponding hour
                int sensorHour = sensorCalendar.get(Calendar.HOUR_OF_DAY);
                hoursAndValues.put(sensorHour, hoursAndValues.get(sensorHour) + sensor.getTotalEnergyConsumption());
            }
        }

        // Build and return the Chart object
        return Chart.builder()
                .idClient(idClient)
                .consumptionPerHour(hoursAndValues)
                .build();
    }

}

