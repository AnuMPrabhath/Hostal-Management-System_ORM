package com.example.hostalmanagementsystem_orm.dao;

import com.example.hostalmanagementsystem_orm.entity.SuperEntity;
import org.hibernate.Session;

import java.util.List;

public interface CrudDAO<T extends SuperEntity> extends SuperDAO{
    Boolean save(T entity, Session session);

    Boolean update(T entity, Session session);

    Boolean delete(String id ,Session session);

    T view(String id,Session session);

    List<T> getAll(Session session);
    String getLastId(Session session);
}
