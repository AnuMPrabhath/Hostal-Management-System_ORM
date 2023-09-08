package com.example.hostalmanagementsystem_orm.bo.custom.impl;

import com.example.hostalmanagementsystem_orm.bo.custom.UserBO;
import com.example.hostalmanagementsystem_orm.dao.DAOFactory;
import com.example.hostalmanagementsystem_orm.dao.custom.UserDAO;
import com.example.hostalmanagementsystem_orm.dto.UserDto;
import com.example.hostalmanagementsystem_orm.entity.User;
import com.example.hostalmanagementsystem_orm.util.Converter;
import com.example.hostalmanagementsystem_orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public Boolean save(UserDto userDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());

        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDAO.save(user, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(UserDto userDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());

        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDAO.update(user, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            userDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public UserDto view(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        User entity = userDAO.view(id, session);
        if (entity != null) {
            UserDto dto = new UserDto();
            dto.setId(entity.getId());
            dto.setPassword(entity.getPassword());
            return dto;
        }
        throw new RuntimeException("User Not Found !");
    }

    @Override
    public List<UserDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<User> allUsers = userDAO.getAll(session);
        if (allUsers.size() > 0) {
            return allUsers.stream().map(user -> Converter.getInstance().toUserDto(user)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty users list!");
    }
}
