package de.louis.task_api.repository;

import de.louis.task_api.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    /*findAll();
    findById(id);
    save(task);
    delete(task);
    deleteById(id);
    existsById(id);*/

    Page<Task> findByCompleted(boolean completed, Pageable pageable);
}
