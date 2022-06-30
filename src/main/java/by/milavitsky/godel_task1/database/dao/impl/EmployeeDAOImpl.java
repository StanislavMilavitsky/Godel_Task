package by.milavitsky.godel_task1.database.dao.impl;

import by.milavitsky.godel_task1.database.dao.EmployeeDAO;
import by.milavitsky.godel_task1.database.dao.FilterEmployeeDAO;
import by.milavitsky.godel_task1.database.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("employee")
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

    public static final String DELETE_EMPLOYEE_BY_ID_SQL = "DELETE FROM employee em" +
            " WHERE em.employee_id = ?;";

    public static final String FIND_ALL_EMPLOYEE_SQL = "SELECT em.employee_id," +
            " first_name," +
            " last_name," +
            " department_id," +
            " job_title, gender," +
            " date_of_birth FROM employee em;";

    public static final String EMPLOYEE_ID = "employee_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String DEPARTMENT_ID = "department_id";
    public static final String JOB_TITLE = "job_title";
    public static final String GENDER = "gender";
    public static final String DATE_OF_BIRTH = "date_of_birth";



    @Override
    public List<Employee> filterByParameters(FilterEmployeeDAO filterEmployeeDAO, int offset, int limit) {
        return null;
    }

    @Override
    public Employee create(Employee employee) {
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
    }

    @Override
    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID_SQL, new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public Employee update(Employee employee) {
        int rows = jdbcTemplate.update(UPDATE_EMPLOYEE_BY_ID_SQL, employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getJobTitle(), employee.getGender(), employee.getEmployeeId());
        return rows > 0L ? findById(employee.getEmployeeId()) : null;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_EMPLOYEE_BY_ID_SQL, id);
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
