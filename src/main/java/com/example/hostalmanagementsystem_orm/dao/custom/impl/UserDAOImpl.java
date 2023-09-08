package com.example.hostalmanagementsystem_orm.dao.custom.impl;

import com.example.hostalmanagementsystem_orm.dao.custom.UserDAO;
import com.example.hostalmanagementsystem_orm.entity.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public Boolean save(User entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(User entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(String id, Session session) {
        User user = new User();
        user.setId(id);
        session.delete(user);
        return true;
    }

    @Override
    public User view(String id, Session session) {
        try (session) {
            User user = session.get(User.class, id);
            System.out.println(user);
            return user;
        }
    }

    @Override
    public List<User> getAll(Session session) {
        try (session) {
            String sql = "From User";
            List list = session.createQuery(sql).list();
            return list;
        }
    }

    @Override
    public String getLastId(Session session) {
        return null;
    }
}
