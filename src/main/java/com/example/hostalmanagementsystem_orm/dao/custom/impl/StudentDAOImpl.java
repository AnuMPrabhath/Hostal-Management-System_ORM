package com.example.hostalmanagementsystem_orm.dao.custom.impl;

import com.example.hostalmanagementsystem_orm.dao.custom.StudentDAO;
import com.example.hostalmanagementsystem_orm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Boolean save(Student entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public Boolean update(Student entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public Boolean delete(String id, Session session) {
        Student student = new Student();
        student.setStudent_id(id);
        session.delete(student);
        System.out.println("Std Dao end");
        return true;
    }

    @Override
    public Student view(String id, Session session) {
        return session.get(Student.class, id);
    }

    @Override
    public List getAll(Session session) {
        String sql = "FROM Student";
        Query query = session.createQuery(sql);
        List<Student> list = query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        Query query = session.createQuery("SELECT student_id FROM Student ORDER BY student_id DESC");
        query.setMaxResults(1);
        List results = query.list();
        return (results.size() == 0) ? null : (String) results.get(0);
    }

    @Override
    public List<Student> searchStudentByText(String text, Session session) {
        Query query = session.createQuery("FROM Student  WHERE name LIKE '%" + text + "%'");
        List<Student> list = query.list();
        return list;
    }
}
