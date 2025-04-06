package com.Monitoring.Service;

import com.Monitoring.Entity.Device;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    List<Device> findByIdClient(long idClient);
    List<Device> findAll();
}
