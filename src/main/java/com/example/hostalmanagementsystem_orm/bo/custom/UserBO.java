package com.example.hostalmanagementsystem_orm.bo.custom;

import com.example.hostalmanagementsystem_orm.bo.SuperBO;
import com.example.hostalmanagementsystem_orm.dto.UserDto;

import java.util.List;

public interface UserBO extends SuperBO {
    Boolean save(UserDto userDto);

    Boolean update(UserDto userDto);

    Boolean delete(String id);

    UserDto view(String id) throws RuntimeException;

    List<UserDto> getAll();
}
