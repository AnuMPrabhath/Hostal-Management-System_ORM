package com.example.hostalmanagementsystem_orm.bo.custom.impl;

import com.example.hostalmanagementsystem_orm.bo.custom.ReservationBO;
import com.example.hostalmanagementsystem_orm.dao.DAOFactory;
import com.example.hostalmanagementsystem_orm.dao.custom.ReservationDAO;
import com.example.hostalmanagementsystem_orm.dto.ReservationDto;
import com.example.hostalmanagementsystem_orm.entity.Reservation;
import com.example.hostalmanagementsystem_orm.util.Converter;
import com.example.hostalmanagementsystem_orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    @Override
    public Boolean save(ReservationDto reservationDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDAO.save(Converter.getInstance().toReservationEntity(reservationDto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Boolean update(ReservationDto reservationDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDAO.update(Converter.getInstance().toReservationEntity(reservationDto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public Boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            reservationDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ReservationDto view(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.getTransaction();
        try (session) {
            return Converter.getInstance().toReservationDto(reservationDAO.view(id, session));
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<ReservationDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Reservation> reservationDtoList = reservationDAO.getAll(session);
        if (reservationDtoList.size() > 0) {
            return reservationDtoList.stream().map(reservation -> Converter.getInstance().toReservationDto(reservation)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        String lastId = reservationDAO.getLastId(session);
        if (lastId == null) {
            return "REC-000001";
        } else {
            String[] split = lastId.split("[R][E][C][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            return (String.format("REC-%06d", lastDigits));
        }
    }
}
