package de.louis.task_api.controller;

import de.louis.task_api.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("hello")
    public Greeting hello(@RequestParam(defaultValue = "Welt") String name){
        return new Greeting("Hallo "+ name);
    }
}
