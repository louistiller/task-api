package de.louis.task_api.service;

import de.louis.task_api.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks= new ArrayList<>();
    private long nextId= 4;

    public TaskService(){
        tasks.add(new Task(1L, "Spring Boot lernen", false));
        tasks.add(new Task(2L, "Git üben", true));
        tasks.add(new Task(3L, "REST verstehen", false));
    }

    public List<Task> getAllTasks() {
        return List.copyOf(tasks);
    }

    public Task getTaskById(Long id){
        for(Task task:tasks){
            if(task.id().equals(id)){
                return task;
            }
        }
        return null;
    }

    public Task createTask(String title){
        Task task= new Task(nextId, title, false);
        nextId++;
        tasks.add(task);
        return task;
    }

}
