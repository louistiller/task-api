package de.louis.task_api.controller;

import de.louis.task_api.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/task")
    public Task getTask(){
        return new Task(1L, "Spring Boot lernen", false);
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return List.of(
                new Task(1L, "Spring Boot lernen", false),
                new Task(2L, "Git ueben", true),
                new Task(3L, "REST verstehen", false)
        );
    }
}
