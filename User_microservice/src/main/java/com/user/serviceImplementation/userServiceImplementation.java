package com.user.serviceImplementation;

import com.user.DTO.UserDTO;
import com.user.model.UserEntity;
import com.user.repository.userRepository;
import com.user.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class userServiceImplementation implements userService {

    @Autowired
    private userRepository userRepo;

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> usersList = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(UserEntity user: usersList){
            userDTOs.add(UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .role(user.getRole()).build());
        }
        return userDTOs;
    }
/*


    @Override
    public User loginUser(String username, String password) {
        User u = userRepo.findByUsername(username);
        System.out.println(u.getPassword());
        if(u != null){
            if(u.getRole() == User_type.CLIENT){
                if(u.getPassword().equals(password)){
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public User loginAdmin(String username, String password) {
        User admin = userRepo.findByUsername(username);
        System.out.println(admin.getRole()  );
        if(admin != null){
            if(admin.getRole() == User_type.ADMIN){
                if(admin.getPassword().equals(password)){
                    return admin;
                }
            }
        }
        return null;
    }

    @Override
    public User changePassword(String username, String oldPass, String newPass) {
        User u = userRepo.findByUsername(username);
        if(u != null) {
            if (u.getPassword().equals(oldPass)) {
                u.setPassword(newPass);
                userRepo.save(u);
                return u;
            }
        }

        return null;
    }
    */

    @Override
    public void register(UserEntity user) {
        userRepo.save(user);
    }

    @Override
    public void delete(Long id) {
        String url = "http://backenddevice:8081/device/updateByUser/"+id;
        //String url = "http://localhost:8081/device/updateByUser/"+id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, String.class);
        userRepo.deleteById(id);
    }
}


