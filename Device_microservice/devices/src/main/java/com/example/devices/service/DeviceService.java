package com.example.devices.service;

import com.example.devices.model.Device;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface DeviceService {

    List<Device> findAll();
    Optional<Device> findById(long id);
    void insertDevice(HttpServletRequest token, Device device);
    void deleteDevice(HttpServletRequest token, Long id);
    List<Device>findByIdClient(Long idUser);
    List<Device> updateDevice(Long idUser);
}
