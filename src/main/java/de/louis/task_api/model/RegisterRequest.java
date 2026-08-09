package de.louis.task_api.model;

public record RegisterRequest(
        String username,
        String password
) {
}
