package com.Monitoring.Repository;

import com.Monitoring.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device findDeviceById(long id);
    List<Device> findDeviceByIdClient(long idClient);
    List<Device> findAll();
}
