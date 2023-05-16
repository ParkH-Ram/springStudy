package study.home.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.home.dto.HomeDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private HttpSession httpSession;

    @GetMapping("")
    public String test(HomeDTO homeDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
//
//        System.out.println("세션" + session);
//        session.setAttribute("이름", session);
        homeDTO.getName();
        homeDTO.getEmail();
        System.out.println(homeDTO.getName());
        session.setAttribute("name", homeDTO.getName());



        return "test";
    }


    @PostMapping("/2")
    public String test2(HomeDTO homeDTO, HttpServletRequest request){

        HttpSession session = request.getSession();

        session.setAttribute("name", homeDTO.getName());
        session.setAttribute("email", homeDTO.getEmail());

        System.out.println("이름은" + session.getAttribute("name"));
        System.out.println("이름은" + session.getAttribute("email"));
        return "2";
    }
}
