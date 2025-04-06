package com.example.devices.serviceImplementation;

import com.example.devices.DTO.DeviceDTO;
import com.example.devices.model.Device;
import com.example.devices.publisher.RabbitMQProducer;
import com.example.devices.repository.DeviceRepository;
import com.example.devices.service.DeviceService;
import com.example.devices.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceServiceImplementation implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepo;
    private final RabbitMQProducer rabbitMQProducer;
    private final JwtService jwtService;

    @Override
    public List<Device> findAll() {
        return deviceRepo.findAll();
    }

    @Override
    public void insertDevice(HttpServletRequest request, Device device) {
        String token = jwtService.extractTokenFromRequest(request);

        if(token == null || token.isEmpty()){
            return;
        }

        String userRole = jwtService.extractRole(token);
        System.out.println(userRole);
        if(userRole.equals("ADMIN")) {
            System.out.println("este egal cu admin");
            deviceRepo.save(device);
/*
        long id = device.getId();
        String url = "http://localhost:8086/deviceID/insertIDdevice?deviceID="+id;
        //String url = "http://backendsensor:8086/deviceID/insertIDdevice?deviceID="+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, String.class);
*/
            DeviceDTO deviceDTO = DeviceDTO.builder()
                    .id(device.getId())
                    .maxHEnergyConsuption(device.getMaxHEnergyConsumption())
                    .idClient(device.getIdClient())
                    .build();

            rabbitMQProducer.sendDevice(deviceDTO);
        }
    }

    @Override
    public void deleteDevice(HttpServletRequest request, Long id) {
        /*
        String url = "http://localhost:8086/deviceID/deleteByIdDevice?deviceID="+id;
        //String url = "http://backendsensor:8086/deviceID/deleteByIdDevice?deviceID="+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);

         */
        String token = jwtService.extractTokenFromRequest(request);

        if(token == null || token.isEmpty()){
            return;
        }

        String userRole = jwtService.extractRole(token);
        System.out.println(userRole);
        if(userRole.equals("ADMIN"))
            deviceRepo.deleteById(id);
    }

    @Override
    public List<Device> findByIdClient(Long idUser) {
        return deviceRepo.findByIdClient(idUser);
    }

    @Override
    public List<Device> updateDevice(Long idUser) {
        List<Device> devices =  deviceRepo.findByIdClient(idUser);
        for(Device device: devices) {
            device.setIdClient(null);
            deviceRepo.save(device);
        }
        return devices;
    }

    public Optional<Device> findById(long id){
        return deviceRepo.findById(id);
    }


}
