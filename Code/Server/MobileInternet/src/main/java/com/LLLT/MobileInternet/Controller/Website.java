package com.LLLT.MobileInternet.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class Website {

    @GetMapping("/")
    public String Index() {

        return "index";
    }
}
