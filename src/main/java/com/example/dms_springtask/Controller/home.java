package com.example.dms_springtask.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
