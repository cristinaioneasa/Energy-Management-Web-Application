package com.Monitoring.Service;

import com.Monitoring.Entity.Device;
import com.Monitoring.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImplementation implements DeviceService{

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public List<Device> findByIdClient(long idClient) {
        return deviceRepository.findDeviceByIdClient(idClient);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
