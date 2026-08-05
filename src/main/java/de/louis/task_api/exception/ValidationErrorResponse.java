package de.louis.task_api.exception;

import java.util.Map;

public record ValidationErrorResponse(int status, Map<String, String> errors) {
}
