package com.example.hostalmanagementsystem_orm.bo.custom.impl;

import com.example.hostalmanagementsystem_orm.bo.custom.StudentBO;
import com.example.hostalmanagementsystem_orm.dao.DAOFactory;
import com.example.hostalmanagementsystem_orm.dao.custom.QueryDAO;
import com.example.hostalmanagementsystem_orm.dao.custom.StudentDAO;
import com.example.hostalmanagementsystem_orm.dto.StudentDto;
import com.example.hostalmanagementsystem_orm.entity.Student;
import com.example.hostalmanagementsystem_orm.util.Converter;
import com.example.hostalmanagementsystem_orm.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public Boolean save(StudentDto studentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDAO.save(Converter.getInstance().toStudentEntity(studentDto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not added");
        }
    }

    @Override
    public Boolean update(StudentDto studentDto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDAO.update(Converter.getInstance().toStudentEntity(studentDto), session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not updated");
        }
    }

    @Override
    public Boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.getTransaction();
        try (session) {
            transaction.begin();
            studentDAO.delete(id, session);
            transaction.commit();
            return true;
        } catch (RuntimeException exception) {
            transaction.rollback();
            throw new RuntimeException("Student not Deleted");
        }
    }

    @Override
    public StudentDto view(String id) throws RuntimeException {
        Session session = FactoryConfiguration.getInstance().getSession();
        try (session) {
            Student student = (Student) studentDAO.view(id, session);
            if (student != null) return Converter.getInstance().toStudentDto(student);
            throw new RuntimeException("Student not found");
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public List<StudentDto> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try (session) {
            List<Student> studentList = studentDAO.getAll(session);
            if (studentList.size() > 0) {
                return studentList.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
            }
        }
        throw new RuntimeException("Empty Student list!");
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try (session) {
            String lastId = studentDAO.getLastId(session);
            if (lastId == null) {
                return "IT000001";
            } else {
                String[] split = lastId.split("[I][T]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                return (String.format("IT%06d", lastDigits));
            }
        }
    }

    @Override
    public List<StudentDto> getUnpaidStudents() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Student> unpaidStudents = queryDAO.getUnpaidStudents(session);
        if (unpaidStudents.size() > 0) {
            return unpaidStudents.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
        }
        throw new RuntimeException("No any unpaid case yet!");
    }

    @Override
    public List<StudentDto> searchStudentByText(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try (session) {
            List<Student> searchStudentByText = studentDAO.searchStudentByText(text, session);
            if (searchStudentByText.size() > 0) {
                return searchStudentByText.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
            }
            throw new RuntimeException("No any match found");
        }

    }
}
