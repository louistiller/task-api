package de.louis.task_api.model;

public record TaskResponse(
        Long id,
        String title,
        boolean completed
) {
}
