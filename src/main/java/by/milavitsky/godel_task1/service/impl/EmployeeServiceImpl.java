package by.milavitsky.godel_task1.service.impl;

import by.milavitsky.godel_task1.database.dao.EmployeeDAO;
import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.mapper.Mapper;
import by.milavitsky.godel_task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = (Employee) mapper.fromDto(employeeDto);
        return null;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<EmployeeDto> findAll() {
        return null;
    }
}
