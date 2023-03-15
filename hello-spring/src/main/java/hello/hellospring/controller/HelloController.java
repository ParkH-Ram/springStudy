package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "whatthe!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // name에 담은 값을 model에 넒겨줌
        model.addAttribute("name", name);  // name에 값을 입력
        return "hello-template";  // hello-mvc?name= "dfdfdfdf"  " "  안 문자가 표현 됨
    }

    @GetMapping("hello-string")    // html 이런거 없이 그대로~ data를  넣어 줌~
    @ResponseBody      // 응답 바디부에 이 내용을 직접 넣어 주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;  // 스프링을 넣으면..  "hello 스프링"  ... 요청한 클라이언트에 그대ㅗㄹ
        // view 이런게 없고 문자가 그대로 내려 감
    }

    // 진짜는 지금부터  API 방식!
    @GetMapping("hello-api")
    @ResponseBody
    public hello helloApi(@RequestParam("name") String name) {
        hello hello = new hello();
        hello.setName(name);
        return hello;   // 문자가 아닌 객체를 넘김   # JSON 타입으로 넘겨줌
        // {"Key" : "Value"}로 값을 넘김
    }

    static class hello {  // static 으로 만들면 클래스 안에서 클래스를 쓸 수 있음
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;


        @Override
        public String toString() {
            return "hello{" +
                    "name='" + name + '\'' +
                    '}';
        }


    }
}
