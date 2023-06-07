package com.tom.webtomcat.controller;


import com.tom.webtomcat.domain.SampleDTO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
@RequestMapping("/sample")
public class SampleController {


    //6-2
    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public String basicGet(){
        log.info("basic...............");
        return "home";
    }

    // 위럼 길게 쓰던걸 이 처럼 축약해서 사용 가능
    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get.............");
    }


    //6-3
    @GetMapping("/ex1")
    public String ex1(SampleDTO dto){
        log.info("" + dto);

        return "ex1";
    }


}
