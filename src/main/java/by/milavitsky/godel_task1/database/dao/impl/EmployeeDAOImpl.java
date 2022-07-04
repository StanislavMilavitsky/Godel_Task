package by.milavitsky.godel_task1.database.dao.impl;

import by.milavitsky.godel_task1.database.dao.EmployeeDAO;
import by.milavitsky.godel_task1.database.dao.FilterEmployeeDAO;
import by.milavitsky.godel_task1.database.entity.Employee;
import by.milavitsky.godel_task1.exception.DAOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.milavitsky.godel_task1.database.dao.constant.ConstantDAO.*;


@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private  SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    private void postConstruct(){
        jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("employee")
                .usingGeneratedKeyColumns("employee_id");
    }

    public static final String FIND_EMPLOYEE_BY_ID_SQL = "SELECT" +
            " em.employee_id," +
            " first_name," +
            " last_name," +
            " department_id," +
            " job_title," +
            " gender," +
            " date_of_birth" +
            " FROM employee em" +
            " WHERE em.employee_id = ?;";

    public static final String UPDATE_EMPLOYEE_BY_ID_SQL = "UPDATE employee em" +
            " SET first_name = ?," +
            " last_name = ?," +
            " department_id = ?,"+
            "job_title = ?, " +
            "gender = ? " +
            "WHERE em.employee_id = ?;";

    public static final String DELETE_EMPLOYEE_BY_ID_SQL = "UPDATE employee em" +
            " SET department_id = 0" +
            " WHERE em.employee_id = ?;";

    public static final String FIND_ALL_EMPLOYEE_SQL = "SELECT em.employee_id," +
            " first_name," +
            " last_name," +
            " department_id," +
            " job_title, gender," +
            " date_of_birth FROM employee em;";

    @Override
    public List<Employee> filterByParameters(FilterEmployeeDAO filterEmployeeDAO, int offset, int limit) {
        return null;
    }

    @Override
    public Employee create(Employee employee) throws DAOException {
        try{
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(FIRST_NAME, employee.getFirstName());
        parameters.put(LAST_NAME, employee.getLastName());
        parameters.put(DEPARTMENT_ID, employee.getDepartmentId());
        parameters.put(JOB_TITLE, employee.getJobTitle());
        parameters.put(GENDER, employee.getGender());
        parameters.put(DATE_OF_BIRTH, employee.getDateOfBirth());
        Number id = jdbcInsert.executeAndReturnKey(parameters);
        employee.setEmployeeId(id.longValue());
        return employee;
    } catch (DataAccessException exception){
        String exceptionMessage = String.format("Create employee by Last name=%s exception sql!", employee.getLastName());
        log.error(exceptionMessage, exception);
        throw new DAOException(exceptionMessage, exception);
    }
    }

    @Override
    public Employee findById(Long id) throws DAOException {
        try {
            return jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID_SQL, new BeanPropertyRowMapper<>(Employee.class), id);
        } catch (DataAccessException exception){
        String exceptionMessage = String.format("Read employee by id=%d exception sql!", id);
        log.error(exceptionMessage, exception);
        throw new DAOException(exceptionMessage, exception);
    }
    }

    @Override
    public Employee update(Employee employee) throws DAOException {
        try {
            int rows = jdbcTemplate.update(UPDATE_EMPLOYEE_BY_ID_SQL, employee.getFirstName(), employee.getLastName(),
                    employee.getDepartmentId(), employee.getJobTitle(), employee.getGender(), employee.getEmployeeId());
            return rows > 0L ? findById(employee.getEmployeeId()) : null;
        } catch (DataAccessException exception) {
            String exceptionMessage = String.format("Update employee by id=%d exception sql!", employee.getEmployeeId());
            log.error(exceptionMessage, exception);
            throw new DAOException(exceptionMessage, exception);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try{
        jdbcTemplate.update(DELETE_EMPLOYEE_BY_ID_SQL, id);
    } catch (DataAccessException exception){
        String exceptionMessage = String.format("Delete employee by id=%d exception sql!", id);
        log.error(exceptionMessage, exception);
        throw new DAOException(exceptionMessage, exception);
    }
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEE_SQL, (rs, rowNum) -> new Employee(
                rs.getLong(EMPLOYEE_ID),
                rs.getString(FIRST_NAME),
                rs.getString(LAST_NAME),
                rs.getLong(DEPARTMENT_ID),
                rs.getString(JOB_TITLE),
                rs.getString(GENDER),
                rs.getDate(DATE_OF_BIRTH).toLocalDate()
        ));
    }

}

