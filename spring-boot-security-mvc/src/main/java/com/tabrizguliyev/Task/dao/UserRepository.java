package com.tabrizguliyev.Task.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.tabrizguliyev.Task.entities.User;

/**
 *
 * @author tabrizguliyev
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByUsername(String username);
}
