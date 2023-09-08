package com.example.hostalmanagementsystem_orm.dao.custom;

import com.example.hostalmanagementsystem_orm.dao.SuperDAO;
import com.example.hostalmanagementsystem_orm.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List <Student>getUnpaidStudents(Session session);
}
