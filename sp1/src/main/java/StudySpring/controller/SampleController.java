package StudySpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

    @GetMapping("/doA")
    public void doA() {

        System.out.println("do.........................................");

    }
}
