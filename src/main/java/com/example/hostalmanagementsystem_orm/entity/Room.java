package com.example.hostalmanagementsystem_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;
}
