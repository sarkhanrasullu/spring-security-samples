package com.tabrizguliyev.Task.service;

import com.tabrizguliyev.Task.dao.TaskRepository;
import com.tabrizguliyev.Task.entities.Task;
import com.tabrizguliyev.Task.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tabrizguliyev
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskServiceInterface {

    @Autowired
    public TaskRepository taskDao;

    @Override
    public List<Task> getAll() {
        List<Task> list = taskDao.findAll();
        return list;

    }

    @Override
    public int add(Task t) {
        taskDao.save(t);
        return t.getId();
    }

    @Override
    public boolean update(Task t) {
        taskDao.save(t);
        return true;
    }

    @Override
    public int delete(int id) {
        taskDao.deleteById(id);
        return id;
    }

    @Override
    public List<Task> findAllByUserId(User userId) {
        return taskDao.findAllByUserId(userId);
    }

    @Override
    public Task findByUserId(User userId) {
        return taskDao.findByUserId(userId);
    }
    
     @Override
    public Task findById(int id) {
        return taskDao.findById(id);
    }

}
