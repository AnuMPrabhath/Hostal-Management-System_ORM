package com.example.hostalmanagementsystem_orm.bo;

import com.example.hostalmanagementsystem_orm.bo.custom.impl.ReservationBOImpl;
import com.example.hostalmanagementsystem_orm.bo.custom.impl.RoomBOImpl;
import com.example.hostalmanagementsystem_orm.bo.custom.impl.StudentBOImpl;
import com.example.hostalmanagementsystem_orm.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT, ROOM, RESERVATION, USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
