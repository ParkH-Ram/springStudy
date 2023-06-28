package com.board.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@Log4j2
public class RestController {

    @GetMapping("/helloArr")
    public String[] helloArr(){
        log.info("helloArr-----------");

        return new String[]{"AAA", "BBB", "CDCDD"};
    }
}
