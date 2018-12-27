package com.tabrizguliyev.Task.service;

import com.tabrizguliyev.Task.entities.User;
import java.util.List;

/**
 *
 * @author tabrizguliyev
 */
public interface UserServiceInterface {

    public User findUserById(int id);

    public List<User> getAll();

    int add(User u);

    boolean update(User u);

    int delete(int id);
}
