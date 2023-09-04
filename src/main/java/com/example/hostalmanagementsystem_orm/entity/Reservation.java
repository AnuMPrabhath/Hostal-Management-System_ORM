package com.example.hostalmanagementsystem_orm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Reservation {
    @Id
    private String res_id;
    private Date date;
    private String status;

}
