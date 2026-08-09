package de.louis.task_api.model;

import jakarta.persistence.*;

import org.springframework.web.bind.annotation.ExceptionHandler;

@Entity


public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id"/*, nullable =false*/)
    private User user;

    protected Task(){}
    public Task(String title, boolean completed, User user){
        this.title= title;
        this.completed= completed;
        this.user=user;
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
