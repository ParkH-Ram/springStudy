package com.tom.webtomcat.controller;


import com.tom.webtomcat.domain.SampleDTO;
import com.tom.webtomcat.domain.TodoDTO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

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
        log.info("ddd");

        return "ex2List";

    }

    // 배열도 동일하게 처리 가능
    @GetMapping("/ex2Array")
    public String ex2Array(@RequestParam("value") String [] value){

        log.info("array value : " + Arrays.toString(value));

        return "ex2Array";

    }

    // 6.3.4 @InitBinder
    // 사용자가 18-1-1과 같이 들어오는 데이터를 변환하고자할 때  생기는 문제를 해결 하기 위한 어노테이션

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/ex3")
    public String ex3(TodoDTO todo){
        log.info("todo : " + todo);
        return "ex3";
    }

    // 6.3.5 @DateTimeFormat  적용 DTO에 한다
    // InitBinder를 이용해 날짜 변환도 가능하지만 파라미터로 사용되는 인스턴스 변수는 @DateTimeFormat을 적용해도 변환 가능
    // @DateTimeFormat을 이용하는 경우 @InitBinder는 필요하지 않는다


    // 6.4 @ModelAttribute는 강제로 파라미터를 Model에 담아서 전달ㅎ도록 할 때 필요한 어노테이션
    @GetMapping("/ex4")
    public String ex4(SampleDTO dto, @ModelAttribute("page") int page ){ // 기본 자료형은 파라미터로 선언해도 기본적으로 화면까지 전달 x
                                                                        // 그렇기 때문에 ModelAttribute로 값을 받아 와야 함
        log.info("dto : " + dto);
        log.info("page : " + page);

        return  "/sample/ex4";
    }

    // 6.5.4 ResponseEntity 타입
    @GetMapping("/ex7")
    public ResponseEntity<String> ex7(){
        log.info("/ex7..............");

        String msg = "{\"name\" : \"홍길동\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        return  new ResponseEntity<>(msg, headers, HttpStatus.OK);
    }

    // 6.5.5 파일 업로드
    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("/exUpload........................");
    }
    //6.5.5 파일 업로드를 받는 메서드
    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        // 스프링 MVC는 전달 되는 파라미터가 동일한 이름으로 여러 개 존재하면 배열로 처리가 가능하므로 파라미터를 MultipartFile의 배열 타입으로 작성
        files.forEach(file -> {
            log.info("----------------------------");
            log.info("name : " + file.getOriginalFilename());
            log.info("size : " + file.getSize());
        });
    }



}
