package de.louis.task_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello(){
        return "Hallo aus meiner Task API";
    }
}
