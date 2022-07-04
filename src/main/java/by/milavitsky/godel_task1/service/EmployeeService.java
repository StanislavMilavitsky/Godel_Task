package by.milavitsky.godel_task1.service;

import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.exception.ServiceException;

import java.util.List;

/**
 *  Service layer use methods from dao layer
 */
public interface EmployeeService {
    /**
     * Use method findById in dao layer
     * @param id is field employee
     * @return entity from database
     * @throws ServiceException if entity by id has not been exist
     */
     EmployeeDto findById(Long id) throws ServiceException;

    /**
     * Use method create in dao layer
     * @param employeeDto entity employee
     * @return created entity
     * @throws ServiceException if the entity has not been added to the database
     */
    EmployeeDto create (EmployeeDto employeeDto) throws ServiceException;


    /**
     * Use method update in dao layer
     * @param employeeDto entity employee
     * @return updated entity
     * @throws ServiceException if the entity has not been updated to the database
     */
    EmployeeDto update (EmployeeDto employeeDto) throws ServiceException;


    /**
     * Use method delete in dao layer
     * @param id is field entity employee
     * @throws ServiceException  if the entity has not been deleted to the database
     */

    void deleteById (Long id) throws ServiceException;

    /**
     * Use method findAll in dao layer
     * @return list of all employees
     */
    List<EmployeeDto> findAll();
}
