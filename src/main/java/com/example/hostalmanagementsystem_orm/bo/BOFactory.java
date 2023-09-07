package com.example.hostalmanagementsystem_orm.bo;

import com.example.hostalmanagementsystem_orm.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
