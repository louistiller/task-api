package de.louis.task_api.service;

import de.louis.task_api.exception.TaskNotFoundException;
import de.louis.task_api.model.Task;
import de.louis.task_api.model.TaskResponse;
import de.louis.task_api.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<TaskResponse> getTasksByCompleted(Boolean completed) {

        List<Task> tasks;

        if (completed == null) {
            tasks = taskRepository.findAll();
        } else {
            tasks = taskRepository.findByCompleted(completed);
        }

        return tasks.stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long id){
        return toResponse(taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id)));
    }

    public Task findTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
    }

    public TaskResponse createTask(String title){
        Task task= new Task(title, false);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse modifyTask(Long id, String title, boolean completed){
        Task task=findTaskById(id);
        task.setTitle(title);
        task.setCompleted(completed);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse deleteTask(Long id){
        Task task= findTaskById(id);
        taskRepository.delete(task);
        return toResponse(task);
    }

}
