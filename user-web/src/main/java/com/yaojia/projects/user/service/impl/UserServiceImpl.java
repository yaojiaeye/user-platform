package com.yaojia.projects.user.service.impl;
import com.yaojia.projects.user.domain.User;
import com.yaojia.projects.user.service.UserService;
import javax.annotation.Resource;
import javax.persistence.EntityManager;


public class UserServiceImpl implements UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Override
    public boolean register(User user) {
        entityManager.persist(user);
        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
