package com.tabrizguliyev.Task.service;

import com.tabrizguliyev.Task.dao.UserRepository;
import com.tabrizguliyev.Task.entities.User;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tabrizguliyev
 */
@Service
@Transactional

public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    private UserRepository userDao;

    @Override
    public User findUserById(int id) {
        Optional<User> op = userDao.findById(id);
        return op.get();

    }

    @Override
    public List<User> getAll() {
        List<User> list = userDao.findAll();
//        List<Users> l = IteratorUtils.toList(itarable.iterator());
        return list;
    }

    @Override
    public int add(User u) {
        userDao.save(u);
        return u.getId();

    }

    @Override
    public boolean update(User u) {
        userDao.save(u);
        return true;

    }

    @Override
    public int delete(int id) {
        userDao.deleteById(id);
        return id;

    }

}
