package com.example.hostalmanagementsystem_orm.dao.custom.impl;

import com.example.hostalmanagementsystem_orm.dao.custom.QueryDAO;
import com.example.hostalmanagementsystem_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Student> getUnpaidStudents(Session session) {
        Query query = session.createQuery("SELECT DISTINCT s FROM Student s LEFT JOIN s.reservationList r LEFT JOIN r.room rm WHERE r.status = 'un-paid'");
        return (List<Student>) query.list();
    }
}
