package de.louis.task_api.controller;

import de.louis.task_api.model.CreateTaskRequest;
import de.louis.task_api.model.ModifyTaskRequest;
import de.louis.task_api.model.Task;
import de.louis.task_api.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request){
        return taskService.createTask(request.title());
    }

    @PutMapping("/{id}")
    public Task modifyTask(@PathVariable Long id, @RequestBody ModifyTaskRequest request){
        return taskService.modifyTask(id, request.title(), request.completed());
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
}
