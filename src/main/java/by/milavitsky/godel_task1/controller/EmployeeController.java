package by.milavitsky.godel_task1.controller;

import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.exception.ControllerException;
import by.milavitsky.godel_task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable(name = "id") Long id) throws ControllerException {
        if (id > 0){
            EmployeeDto employeeDto = employeeService.findById(id);
            return ResponseEntity.ok(employeeDto);
        } else {
            log.error("Negative id exception");
            throw  new ControllerException("Negative id exception");
        }
    }
}
