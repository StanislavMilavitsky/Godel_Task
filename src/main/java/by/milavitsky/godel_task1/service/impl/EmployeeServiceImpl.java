package by.milavitsky.godel_task1.service.impl;

import by.milavitsky.godel_task1.database.dao.EmployeeDAO;
import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.mapper.Mapper;
import by.milavitsky.godel_task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeDAO employeeDAO;
   private final Mapper mapper;

    @Override
    public EmployeeDto findById(Long id) {
        return (EmployeeDto) mapper.toDto(employeeDAO.findById(id));
    }

    @Transactional
    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = (Employee) mapper.fromDto(employeeDto);
        return (EmployeeDto) mapper.toDto(employeeDAO.create(employee));
    }

    @Transactional
    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeDAO.update((Employee) mapper.fromDto(employeeDto));
        return (EmployeeDto) mapper.toDto(employee);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeDAO.delete(id);
    }

    @Override
    public List<EmployeeDto> findAll() {
        return (List<EmployeeDto>) mapper.toDto(employeeDAO.findAll());
    }
}
