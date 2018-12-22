package com.company.service;

import com.company.entity.User;
import java.util.List;

/**
 *
 * @author tabrizguliyev
 */
public interface UserServiceInterface {

    public User findUserById(int id);
    public List<User> findUserByNameAndSurname(String name, String surname);

    public List<User> getAll();

    int add(User u);

    boolean update(User u);

    int delete(int id);
}
