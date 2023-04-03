package com.shopping.project.controller;


import com.shopping.project.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public UserDTO test(){

        UserDTO userDTO = new UserDTO();
        userDTO.setAge(33);
        userDTO.setName("ram");

        return userDTO;
    }

}
