package de.louis.task_api.service;

import de.louis.task_api.exception.TaskNotFoundException;
import de.louis.task_api.model.Task;
import de.louis.task_api.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()->new TaskNotFoundException(id));
    }

    public Task createTask(String title){
        Task task= new Task(title, false);
        return taskRepository.save(task);
    }

    public Task modifyTask(Long id, String title, boolean completed){
        Task task=getTaskById(id);
        task.setTitle(title);
        task.setCompleted(completed);
        return taskRepository.save(task);
    }

    public Task deleteTask(Long id){
        Task task=getTaskById(id);
        taskRepository.delete(task);
        return task;
    }

}
