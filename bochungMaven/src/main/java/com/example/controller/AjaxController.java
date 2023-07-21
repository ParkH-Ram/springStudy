package com.example.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Controller
public class AjaxController {

    @GetMapping(value = "/")
    public String Home(){
        return "index";
    }

    //Test Case - 1
    @ResponseBody
    @GetMapping(value="/list",consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<String> list(@ModelAttribute("userName") String userName, @RequestParam("password") String password){
        log.info("Request List.......");
        log.info("userName : " + userName);
        log.info("password : " + password);
        List<String> response = new ArrayList<>();
        response.add(userName);
        response.add(password);
        return response;
    }

}
