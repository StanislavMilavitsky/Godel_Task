package by.milavitsky.godel_task1.database.dao;

import by.milavitsky.godel_task1.database.entity.Employee;

import java.util.List;

public interface BaseDAO <T> {

    T create (T entity);

    Employee findById(Long id);

    T update (T entity);

    void delete(Long id);

    List<T> findAll();

}
