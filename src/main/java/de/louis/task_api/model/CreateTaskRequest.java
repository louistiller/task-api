package de.louis.task_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank(message = "Title must not be blank")
        @Size(max=100, message = "Title must not exceed 100 characters")
        String title
) {
}
