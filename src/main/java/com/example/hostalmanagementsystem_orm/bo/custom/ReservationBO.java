package com.example.hostalmanagementsystem_orm.bo.custom;

import com.example.hostalmanagementsystem_orm.bo.SuperBO;
import com.example.hostalmanagementsystem_orm.dto.ReservationDto;

import java.util.List;

public interface ReservationBO extends SuperBO {
    Boolean save(ReservationDto reservationDto);

    Boolean update(ReservationDto reservationDto);

    Boolean delete(String id);

    ReservationDto view(String id) throws RuntimeException;

    List<ReservationDto> getAll();
    String getLastId();
}
