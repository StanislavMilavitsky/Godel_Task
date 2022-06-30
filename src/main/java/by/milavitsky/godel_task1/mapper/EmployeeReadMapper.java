package by.milavitsky.godel_task1.mapper;

import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmployeeReadMapper implements Mapper<EmployeeDto, Employee> {

    @Override
    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTitle(),
                employee.getGender(),
                employee.getDateOfBirth().toString()
        );
    }

    @Override
    public Employee fromDto(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getEmployeeId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getDepartmentId(),
                employeeDto.getJobTitle(),
                employeeDto.getGender(),
                LocalDate.parse(employeeDto.getDateOfBirth())
        );
    }
}
