package com.example.hostalmanagementsystem_orm.bo.custom;

import com.example.hostalmanagementsystem_orm.bo.SuperBO;
import com.example.hostalmanagementsystem_orm.dto.StudentDto;

import java.util.List;

public interface StudentBO extends SuperBO {
    Boolean save(StudentDto studentDto);

    Boolean update(StudentDto studentDto);

    Boolean delete(String id);

    StudentDto view(String id) throws RuntimeException;

    List<StudentDto> getAll();

    String getLastId();

    List <StudentDto>getUnpaidStudents();

    List<StudentDto>searchStudentByText(String text);
}
