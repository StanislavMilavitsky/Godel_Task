package by.milavitsky.godel_task1.database.dao;

import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.database.entity.EmployeeFilter;

import java.util.List;

public interface FilterEmployeeDAO {

    List<Employee> findAllByFilter(EmployeeFilter filter);
}
