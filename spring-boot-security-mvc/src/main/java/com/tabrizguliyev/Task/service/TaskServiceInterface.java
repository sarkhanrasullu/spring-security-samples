package com.tabrizguliyev.Task.service;

import com.tabrizguliyev.Task.entities.Task;
import com.tabrizguliyev.Task.entities.User;
import java.util.List;

/**
 *
 * @author tabrizguliyev
 */
public interface TaskServiceInterface {

    public List<Task> getAll();

    int add(Task t);

    boolean update(Task t);

    int delete(int id);

    List<Task> findAllByUserId(User userId);
    
    Task findByUserId(User userId);

    Task findById(int id);

}
