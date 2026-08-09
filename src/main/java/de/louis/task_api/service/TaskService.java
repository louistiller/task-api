package de.louis.task_api.service;

import de.louis.task_api.exception.TaskNotFoundException;
import de.louis.task_api.model.Task;
import de.louis.task_api.model.TaskPageResponse;
import de.louis.task_api.model.TaskResponse;
import de.louis.task_api.model.User;
import de.louis.task_api.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService= userService;
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

    public TaskPageResponse getTasksByCompleted(String username, Boolean completed, Pageable pageable) {

        Page<Task> taskPage;

        if (completed == null) {
            taskPage = taskRepository.findByUserUsername(username, pageable);
        } else {
            taskPage = taskRepository.findByUserUsernameAndCompleted(username, completed, pageable);
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

    public TaskResponse getTaskById(Long id, String username){
        return toResponse(findTaskById(id, username));
    }

    public Task findTaskById(Long id, String username){
        return taskRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(()->new TaskNotFoundException(id));
    }

    public TaskResponse createTask(String title, String username){
        User user= userService.findByUsername(username);

        Task task= new Task(title, false, user);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse modifyTask(Long id, String username, String title, boolean completed){
        Task task=findTaskById(id, username);
        task.setTitle(title);
        task.setCompleted(completed);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse deleteTask(Long id, String username){
        Task task= findTaskById(id, username);
        taskRepository.delete(task);
        return toResponse(task);
    }

}
