package com.example.hostalmanagementsystem_orm.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class User implements SuperEntity{
    @Id
    private String id;
    private String password;
}
