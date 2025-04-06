package com.user.service;

import com.user.DTO.UserDTO;
import com.user.model.UserEntity;

import java.util.List;

public interface userService {

    List<UserDTO> findAll();
    void register(UserEntity user);
    void delete(Long id);
}
