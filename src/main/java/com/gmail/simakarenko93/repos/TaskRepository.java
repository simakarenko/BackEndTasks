package com.gmail.simakarenko93.repos;

import com.gmail.simakarenko93.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAccountLogin(String login, Pageable pageable);

    Long countByAccountLogin(String login);
}
