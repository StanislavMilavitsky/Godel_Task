package by.milavitsky.godel_task1.database.dao;


import by.milavitsky.godel_task1.database.entity.Employee;

import java.util.List;

/**
 * Work with employee certificate in layer DAO
 */
public interface EmployeeDAO extends BaseDAO<Employee> {

    List<Employee> filterByParameters(FilterEmployeeDAO filterParams, int offset, int limit);
}
