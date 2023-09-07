package com.example.hostalmanagementsystem_orm.dao;

import com.example.hostalmanagementsystem_orm.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public enum BOTypes{
        STUDENT
    }

    //Object creation logic for BO objects
    public SuperDAO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }
}
