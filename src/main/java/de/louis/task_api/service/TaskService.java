package de.louis.task_api.service;

import de.louis.task_api.exception.TaskNotFoundException;
import de.louis.task_api.model.Task;
import de.louis.task_api.model.TaskPageResponse;
import de.louis.task_api.model.TaskResponse;
import de.louis.task_api.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

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

    public TaskPageResponse getTasksByCompleted(Boolean completed, Pageable pageable) {

        Page<Task> taskPage;

        if (completed == null) {
            taskPage = taskRepository.findAll(pageable);
        } else {
            taskPage = taskRepository.findByCompleted(completed, pageable);
        }

        List<TaskResponse> content = taskPage
                .getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new TaskPageResponse(
                content,
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalElements(),
                taskPage.getTotalPages(),
                taskPage.isFirst(),
                taskPage.isLast()
        );
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
