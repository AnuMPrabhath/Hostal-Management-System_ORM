package com.example.hostalmanagementsystem_orm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Student implements SuperEntity{
    @Id
    private  String student_id;
    private String name;
    private String address;
    private String contact_no;
    private LocalDate date;
    private String gender;
    @ToString.Exclude
    @OneToMany(targetEntity = Reservation.class, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
}
