package com.user.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.model.User_type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private User_type role;
    private Long id;
    private String username;
}
