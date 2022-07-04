package by.milavitsky.godel_task1.service.impl;

import by.milavitsky.godel_task1.database.dao.EmployeeDAO;
import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.exception.DAOException;
import by.milavitsky.godel_task1.exception.ServiceException;
import by.milavitsky.godel_task1.mapper.Mapper;
import by.milavitsky.godel_task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
   private final EmployeeDAO employeeDAO;
   private final Mapper<EmployeeDto, Employee> mapper;

    @Override
        public EmployeeDto findById(Long id) throws ServiceException {
        try {
        return  mapper.toDto(employeeDAO.findById(id));
    } catch (DAOException exception) {
        String exceptionMessage = String.format("Cant find employee by id=%d !", id);
        log.error(exceptionMessage, exception);
        throw new ServiceException(exceptionMessage, exception);
    }
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) throws ServiceException {
        try{
        Employee employee = mapper.fromDto(employeeDto);
        return mapper.toDto(employeeDAO.create(employee));
    } catch (DAOException exception) {
        String exceptionMessage = String.format("Add employee by Last name=%s exception!", employeeDto.getLastName());
        log.error(exceptionMessage, exception);
        throw new ServiceException(exceptionMessage, exception);
    }
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws ServiceException {
        try{
        Employee employee = employeeDAO.update(mapper.fromDto(employeeDto));
        return mapper.toDto(employee);
    } catch (DAOException exception) {
        String exceptionMessage = String.format("Update employee by Last name=%s exception!", employeeDto.getLastName());
        log.error(exceptionMessage, exception);
        throw new ServiceException(exceptionMessage, exception);
    }
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        try{
        employeeDAO.delete(id);
    } catch (DAOException exception) {
        String exceptionMessage = String.format("Delete employee by id=%d exception!", id);
        log.error(exceptionMessage, exception);
        throw new ServiceException(exceptionMessage, exception);
    }
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employeeDao = employeeDAO.findAll();
        return employeeDao.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
