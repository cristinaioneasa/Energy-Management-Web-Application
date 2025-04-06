package com.Monitoring.Repository;

import com.Monitoring.Entity.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Long> {

    List<Sensor> findByIdClient(Long idClient);
}
