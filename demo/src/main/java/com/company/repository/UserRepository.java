package com.company.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author tabrizguliyev
 */
//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 
    
    
    public List<User> findUserByNameAndSurname(String name, String surname);
    
    public User findByUsername(String username);
}
