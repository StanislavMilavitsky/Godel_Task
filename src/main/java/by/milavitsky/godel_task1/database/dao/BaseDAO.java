package by.milavitsky.godel_task1.database.dao;

import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.exception.DAOException;

import java.util.List;

public interface BaseDAO <T> {

    /**
     * Create an entity in database with fields
     * @return entity
     * @throws DAOException if have not been created
     */
    T create (T entity) throws DAOException;

    /**
     * Read an entity from database by id
     * @param id field from entity
     * @return entity tag
     *@throws DAOException if entity have not been found
     */
    Employee findById(Long id) throws DAOException;

    /**
     * Update entity in database without id and create date
     * @return updated entity if update and null if entity has not been updated
     * @throws DAOException if entity have not been update
     */
    T update (T entity) throws DAOException;

    /**
     * Delete entity from database by id
     * @param id field
     * @throws DAOException if certificate have not been deleted
     */
    void delete(Long id) throws DAOException;

    /**
     * Find all in database
     * @return list of entity
     */
    List<T> findAll();

}
