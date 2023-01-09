package com.LLLT.MobileInternet.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @RequestMapping("/")
    public List<String> HelloWorld() {

        return List.of("Hello", "World");
    }

    @RequestMapping("/home")

    public String Home() {

        return "<html><head><title>Home</title><body><h1>dddd</h1></body></head></html>";
    }
}