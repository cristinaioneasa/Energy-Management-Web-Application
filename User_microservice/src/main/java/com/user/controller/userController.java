package com.user.controller;

import com.user.DTO.UserDTO;
import com.user.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4201") //4201 pentru docker
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")

public class userController {

    @Autowired
    userService userService;

    @GetMapping("/findAll")
    public List<UserDTO>findAll(){
        return userService.findAll();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello");
    }

    @DeleteMapping("/deleteUser/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

}
