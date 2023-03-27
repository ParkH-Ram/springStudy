package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

   @GetMapping("hello")
   public String hello (Model model){
      model.addAttribute("data", "hello!!");
      return "hello";
   }
   @GetMapping("hello-mvc")
   //외부에서 Parameter를 받기 위해
   public String helloMvc(@RequestParam("name") String name, Model model) {

      //초록색 name 이 key 고 하얀색 name이 Value
      model.addAttribute("name", name);
      return "hello-template";
   }

   @GetMapping("hello-string")
   @ResponseBody
   public String helloString(@RequestParam("name") String name){
      return "hello" + name;
   }

   @GetMapping("hello-api")
   @ResponseBody
   public Hello helloApi(@RequestParam("name") String name){
      Hello hello = new Hello();
      //ctrl + shift + ente

      //Parameter로 넘어 온 name을 넣어줌
      hello.setName(name);
      return hello;   // 객체를 리턴해줌



   }

   static class Hello{
      private String name;

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }
   }



}