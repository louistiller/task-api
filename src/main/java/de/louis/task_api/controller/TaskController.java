package de.louis.task_api.controller;

import de.louis.task_api.model.*;
import de.louis.task_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TaskPageResponse> getTasks(@RequestParam(required = false) Boolean completed, @PageableDefault(size=10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.ok(taskService.getTasksByCompleted(completed, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request.title()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> modifyTask(@PathVariable Long id, @Valid @RequestBody ModifyTaskRequest request){
        return ResponseEntity.ok(taskService.modifyTask(id, request.title(), request.completed()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
