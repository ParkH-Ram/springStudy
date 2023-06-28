package com.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model){
        log.info("hello---------------------");

        model.addAttribute("msg","Hello World");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model){

        List<String> list = Arrays.asList("aaaa", "bbbb", "cccc", "ddddd");

        model.addAttribute("list", list);
    }


}
