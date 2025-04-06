package com.user.auth;

import com.user.config.JwtService;
import com.user.model.User_type;
import com.user.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.model.UserEntity;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final userRepository repository;
    private final PasswordEncoder passEncoder;
    private final JwtService jwtServive;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println("verificare inanite de service");
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passEncoder.encode(request.getPassword()))
                .role(User_type.CLIENT)
                .build();
        System.out.println(user.getName());
        repository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());

        var jwtToken = jwtServive.generateToken(extraClaims, user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passEncoder.encode(request.getPassword()))
                .role(User_type.ADMIN)
                .build();
        repository.save(user);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());

        var jwtToken = jwtServive.generateToken(extraClaims, user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.getUsername(), request.getPassword())
        );
        var user = repository.findByUsername(request.getUsername()).orElseThrow();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        var jwtToken = jwtServive.generateToken(extraClaims, user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .role(user.getRole())
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
