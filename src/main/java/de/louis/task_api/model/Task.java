package de.louis.task_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.web.bind.annotation.ExceptionHandler;

@Entity


public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;

    protected Task(){}
    public Task(String title, boolean completed){
        this.title= title;
        this.completed= completed;
    }

    public Long getId() {

        return id;

    }

    public String getTitle() {

        return title;

    }

    public boolean isCompleted() {

        return completed;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public void setCompleted(boolean completed) {

        this.completed = completed;

    }

}
