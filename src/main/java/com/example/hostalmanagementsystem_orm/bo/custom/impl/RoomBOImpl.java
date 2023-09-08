package com.example.hostalmanagementsystem_orm.bo.custom.impl;

import com.example.hostalmanagementsystem_orm.bo.custom.RoomBO;
import com.example.hostalmanagementsystem_orm.dao.DAOFactory;
import com.example.hostalmanagementsystem_orm.dao.custom.RoomDAO;
import com.example.hostalmanagementsystem_orm.dto.RoomDto;
import com.example.hostalmanagementsystem_orm.entity.Room;
import com.example.hostalmanagementsystem_orm.util.Converter;
import com.example.hostalmanagementsystem_orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public Boolean save(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            roomDAO.save(Converter.getInstance().toRoomEntity(roomDto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Boolean update(RoomDto roomDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            roomDAO.update(Converter.getInstance().toRoomEntity(roomDto), session);
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
            roomDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public RoomDto view(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Room entity = roomDAO.view(id, session);
        if (entity != null) {
            return Converter.getInstance().toRoomDto(entity);
        }
        throw new RuntimeException("Room not found!");
    }

    @Override
    public List<RoomDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Room> roomList = roomDAO.getAll(session);
        if (roomList.size() > 0) {
            return roomList.stream().map(room -> Converter.getInstance().toRoomDto(room)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = roomDAO.getLastId(session);
        if (lastId == null) {
            return "RM-0001";
        } else {
            String[] split = lastId.split("[R][M][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("RM-%04d", lastDigits));
        }
    }
}
