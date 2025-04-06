package com.user.DTO;

import com.user.model.User_type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private User_type role;
}
