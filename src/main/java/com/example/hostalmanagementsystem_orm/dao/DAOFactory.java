package com.example.hostalmanagementsystem_orm.dao;

import com.example.hostalmanagementsystem_orm.dao.custom.impl.QueryDAOImpl;
import com.example.hostalmanagementsystem_orm.dao.custom.impl.RoomDAOImpl;
import com.example.hostalmanagementsystem_orm.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        STUDENT, QUERY, ROOM
    }

    //Object creation logic for BO objects
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            default:
                return null;
        }
    }
}
