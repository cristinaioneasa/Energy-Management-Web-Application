package com.example.devices.controller;

import com.example.devices.model.Device;
import com.example.devices.service.DeviceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4201/") //4201 pentru docker
//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/insertDevice")
    public void insertDevice(HttpServletRequest token, @RequestBody Device device){
        deviceService.insertDevice(token, device);
        //return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public List<Device> findAllDevices(){
        return deviceService.findAll();
        //return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/findById")
    public Optional<Device> findById(@RequestParam long id){
        return deviceService.findById(id);
    }

    @DeleteMapping("/deleteDevice")
    public void deleteDevice(HttpServletRequest token, @RequestParam long id){
        deviceService.deleteDevice(token, id);
    }

    @GetMapping("/findByClient")
    public List<Device>findByClient(@RequestParam Long id_client){
        return deviceService.findByIdClient(id_client);
    }

    @PutMapping("/updateByUser/{idUser}")
    public List<Device>deleteByUser(@PathVariable Long idUser){
        return deviceService.updateDevice(idUser);
    }
}
