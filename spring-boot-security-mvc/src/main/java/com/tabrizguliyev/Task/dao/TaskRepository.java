package com.tabrizguliyev.Task.dao;

import com.tabrizguliyev.Task.entities.Task;
import com.tabrizguliyev.Task.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tabrizguliyev
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByUserId(User userId);

    Task findByUserId(User userId);
    
    Task findById(int id);
    
    
}
