package com.user;

import com.user.auth.AuthenticationService;
import com.user.model.UserEntity;
import com.user.model.User_type;
import com.user.repository.userRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication

public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            userRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {

        return args -> {
/*
            var admin = UserEntity.builder()
                    .name("Admin")
                    .username("admin1234@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(User_type.ADMIN)
                    .build();

            userRepository.save(admin);

*/
        };
    }
}