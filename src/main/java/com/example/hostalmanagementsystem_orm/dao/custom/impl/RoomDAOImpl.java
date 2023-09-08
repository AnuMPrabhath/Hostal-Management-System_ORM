package com.example.hostalmanagementsystem_orm.dao.custom.impl;

import com.example.hostalmanagementsystem_orm.dao.custom.RoomDAO;
import com.example.hostalmanagementsystem_orm.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public Boolean save(Room entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(Room entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(String id, Session session) {
        session.delete(id, Room.class);
        return true;
    }

    @Override
    public Room view(String id, Session session) {
        try (session) {
            return session.get(Room.class, id);
        }
    }

    @Override
    public List<Room> getAll(Session session) {
        try (session) {
            String sql = "FROM Room";
            Query query = session.createQuery(sql);
            List list = query.list();
            return list;
        }
    }

    @Override
    public String getLastId(Session session) {
        try (session) {
            Query query = session.createQuery("SELECT room_type_id FROM Room ORDER BY room_type_id DESC");
            query.setMaxResults(1);
            List results = query.list();
            return (results.size() == 0) ? null : (String) results.get(0);
        }
    }
}
