package com.Monitoring.Service;

import com.Monitoring.Entity.Chart;
import com.Monitoring.Entity.Sensor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface SensorService {

    List<Sensor> findByIdClient(Long idClient);
    public Chart getSensorsByClient(HttpServletRequest request, Long timpstamp, Long idClient) throws ParseException;
}
