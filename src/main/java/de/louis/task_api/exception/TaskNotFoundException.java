package de.louis.task_api.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task mit ID "+ id+" wurde nicht gefunden.");
    }
}
