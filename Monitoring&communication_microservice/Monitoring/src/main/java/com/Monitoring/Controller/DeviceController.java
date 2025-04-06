package com.Monitoring.Controller;

import com.Monitoring.Entity.Device;
import com.Monitoring.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deviceController")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/findByIdClient")
    public List<Device> findByIdClient(@RequestParam long idClient){
        return deviceService.findByIdClient(idClient);
    }

    @GetMapping("/findAll")
    public List<Device> findAll(){
        return deviceService.findAll();
    }
}
