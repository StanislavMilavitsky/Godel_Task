package by.milavitsky.godel_task1.service;

import by.milavitsky.godel_task1.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

     EmployeeDto findById(Long id);

    EmployeeDto create (EmployeeDto employeeDto);

    EmployeeDto update (EmployeeDto employeeDto);

    void deleteById (Long id);

    List<EmployeeDto> findAll();
}
