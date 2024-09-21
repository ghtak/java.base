package com.ghtak.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/hello")
    @ResponseBody
    public String index(){
        return "Hello";
    }

    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
