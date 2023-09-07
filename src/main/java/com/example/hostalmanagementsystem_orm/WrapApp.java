package com.example.hostalmanagementsystem_orm;

import com.example.hostalmanagementsystem_orm.entity.Student;
import com.example.hostalmanagementsystem_orm.entity.User;
import com.example.hostalmanagementsystem_orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class WrapApp {
    public static void main(String[] args) {
        AppInitializer.main(args);

    }
}
