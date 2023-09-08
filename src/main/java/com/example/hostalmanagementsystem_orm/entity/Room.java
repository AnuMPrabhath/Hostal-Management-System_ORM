package com.example.hostalmanagementsystem_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
public class Room implements SuperEntity{
    @Id
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;

    @ToString.Exclude
    @OneToMany(mappedBy = "room", targetEntity = Reservation.class)
    List<Reservation> reservationList = new ArrayList<>();
}
