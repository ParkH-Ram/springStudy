package com.tom.webtomcat.controller;


import com.tom.webtomcat.domain.SampleDTO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@Log4j
@RequestMapping("/sample/*")
public class SampleController {


    //6-2
    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public String basicGet(){
        log.info("basic...............");
        return "home";
    }

    // 위처럼 길게 쓰던걸 이 처럼 축약해서 사용 가능
    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get.............");
    }

    //6.3
    @GetMapping("/ex1") //name=aaa&age=59
    public String ex1(SampleDTO dto){
        log.info("" + dto);

        return "ex1";
    }

    //6.3.1
    @GetMapping("/ex2") //name=aaa&age=59
    public String ex2(@RequestParam("name") String name,
                      @RequestParam("age") int age){
        log.info("name : " + name);
        log.info("age : " + age);

        return "ex2";
    }

    //6.3.2
    @GetMapping("/ex2List") // 배열로 처리  ?value=111&value=222&value=333
    public String ex2List(@RequestParam("value")ArrayList<String> value){
        log.info("value : " + value);
        log.info("와휴");

        return "ex2List";

    }

}
