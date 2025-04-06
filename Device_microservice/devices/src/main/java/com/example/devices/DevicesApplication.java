package com.example.devices;

import com.example.devices.model.Device;
import com.example.devices.repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DevicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevicesApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner init(DeviceRepository deviceRepository) {
		return args -> {
			Device d1 = new Device();
			d1.setAddress("udfg");
			d1.setDescription("vsdfg");
			d1.setMaxHEnergyConsumption(0.5F);

			deviceRepository.save(d1);
		};


	}

 */
}
