package com.example.devices.repository;

import com.example.devices.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Optional<Device> findById(Long id);
    void deleteById(Long id);
    List<Device> findAll();
    List<Device>findByIdClient(Long idUser);
}
