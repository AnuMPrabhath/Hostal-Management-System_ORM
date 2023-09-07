package com.example.hostalmanagementsystem_orm.dao;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll();
    boolean add(T dto);
    boolean update(T dto);
    boolean delete(String id);
    List<T> search(String id);
}
