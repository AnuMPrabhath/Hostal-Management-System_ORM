package com.example.hostalmanagementsystem_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    private  String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date date;
    private String gender;
}
