package com.example.hostalmanagementsystem_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
