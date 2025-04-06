package com.Monitoring.Repository;

import com.Monitoring.Entity.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepository extends CrudRepository<Sensor, Long> {
}
