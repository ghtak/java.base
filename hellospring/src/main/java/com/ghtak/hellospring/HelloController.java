package com.ghtak.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String Hello() {
        HelloLombok lombok = new HelloLombok();
        lombok.setName("testName");
        return "hello spring dev tools " + lombok.getName();
    }
}
