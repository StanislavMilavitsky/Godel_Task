package by.milavitsky.godel_task1.controller;

import by.milavitsky.godel_task1.dto.EmployeeDto;
import by.milavitsky.godel_task1.exception.ControllerException;
import by.milavitsky.godel_task1.exception.ServiceException;
import by.milavitsky.godel_task1.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Employee RestAPI.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    /**
     * Find employee by id.
     *
     * @param id the id
     * @return the response entity
     * @throws ServiceException the service exception
     * @throws ControllerException if id is incorrect
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable(name = "id") Long id) throws ControllerException, ServiceException {
        if (id > 0){
            EmployeeDto employeeDto = employeeService.findById(id);
            return ResponseEntity.ok(employeeDto);
        } else {
            log.error("Negative id exception");
            throw  new ControllerException("Negative id exception");
        }
    }

    /**
     * Add employee.
     *
     * @param employeeDto the employee dto
     * @return the response entity
     * @throws ServiceException the service exception
     * @throws ControllerException if entity fields not valid
     */

    @PostMapping()
    public ResponseEntity<EmployeeDto> create(@RequestBody @Valid EmployeeDto employeeDto, BindingResult bindingResult)
            throws ServiceException, ControllerException {
        if (bindingResult.hasErrors()){
            log.error(bindingResultHandler(bindingResult));
            throw new ControllerException(bindingResultHandler(bindingResult));
        } else {
            EmployeeDto result = employeeService.create(employeeDto);
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Update employee. Mark the fields that are not specified for updating null.
     *
     * @param employeeDto the employee dto
     * @return the response entity
     * @throws ServiceException the service exception
     * @throws ControllerException if entity fields not valid
     */
    @PutMapping()
    public ResponseEntity<EmployeeDto> update(@RequestBody @Valid EmployeeDto employeeDto,
                                                     BindingResult bindingResult) throws ServiceException, ControllerException{
        if (bindingResult.hasErrors()){
            log.error(bindingResultHandler(bindingResult));
            throw new ControllerException(bindingResultHandler(bindingResult));
        } else {
            EmployeeDto result = employeeService.update(employeeDto);
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Delete employee by id.
     *
     * @param id the id
     * @return the response entity
     * @throws ServiceException the service exception
     * @throws ControllerException if id is incorrect
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) throws ServiceException, ControllerException {
        if (id > 0) {
            employeeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            throw new ControllerException("Negative id exception");
        }

    }

    /**
     * Get default message by validate exception
     *
     * @param bindingResult exceptions of validate
     * @return string default message of exception
     */
    private String bindingResultHandler(BindingResult bindingResult){
        return bindingResult.getAllErrors().get(0).getDefaultMessage();
    }

}
