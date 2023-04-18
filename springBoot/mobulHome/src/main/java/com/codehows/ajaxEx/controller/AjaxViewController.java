package com.codehows.ajaxEx.controller;

import com.codehows.ajaxEx.dto.AjaxDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxViewController {
    @GetMapping("/ajax-ex-01")
    public String ajaxEx01() {
        return "ajax-ex-01";
    }

    @GetMapping("/ajax-ex-05")
    public String ajaxEx05() {
        return "ajax-ex-05";
    }

    @GetMapping("/ajax-ex-06")
    public String ajaxEx06() {
        return "ajax-ex-06";
    }
}