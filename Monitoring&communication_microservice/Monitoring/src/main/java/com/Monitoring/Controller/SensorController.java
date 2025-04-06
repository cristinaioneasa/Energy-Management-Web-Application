package com.Monitoring.Controller;

import com.Monitoring.Entity.Chart;
import com.Monitoring.Entity.Sensor;
import com.Monitoring.Service.SensorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping ("/sensor")
public class SensorController {

    @Autowired
    SensorService sensorService;

    /*
    @GetMapping("/daily")
    public List<Sensor> getDailyEnergyConsumption(
            //@RequestParam Long idClient,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDay) {

        //List<Sensor> energyConsumption = sensorService.getEnergyConsumptionForDay(idClient, selectedDay);

        List<Sensor> sensors = sensorService.listSensorOneDay(selectedDay);

        System.out.println("In controller: lista de sensori => " + sensors);

        return sensors;
    }

     */

    @GetMapping("/findByClient")
    public List<Sensor> findByClient(@RequestParam long idClient){
        return sensorService.findByIdClient(idClient);
    }

    @GetMapping("/daily")
    public Chart consumptionPerClient(HttpServletRequest request, @RequestParam long timestamp, long idClient) throws ParseException {

        return sensorService.getSensorsByClient(request, timestamp, idClient);
    }
}
