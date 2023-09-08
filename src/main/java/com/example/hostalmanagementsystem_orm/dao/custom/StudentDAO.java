package com.example.hostalmanagementsystem_orm.dao.custom;

import com.example.hostalmanagementsystem_orm.dao.CrudDAO;
import com.example.hostalmanagementsystem_orm.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<Student> searchStudentByText(String text, Session session);
}
