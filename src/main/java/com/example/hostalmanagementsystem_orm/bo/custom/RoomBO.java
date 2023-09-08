package com.example.hostalmanagementsystem_orm.bo.custom;

import com.example.hostalmanagementsystem_orm.bo.SuperBO;
import com.example.hostalmanagementsystem_orm.dto.RoomDto;

import java.util.List;

public interface RoomBO extends SuperBO {
    Boolean save(RoomDto roomDto);

    Boolean update(RoomDto roomDto);

    Boolean delete(String id);

    RoomDto view(String id) throws RuntimeException;

    List<RoomDto> getAll();
    String getLastId();
}
