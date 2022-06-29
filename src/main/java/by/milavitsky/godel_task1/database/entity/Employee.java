package by.milavitsky.godel_task1.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee implements Serializable {

    @Positive(message = "Should be positive")
    private Long employeeId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastName;

    @Positive(message = "Should be positive")
    private Long departmentId;

    @NotEmpty(message = "Must be job title")
    @Size(min = 2, max = 30, message = "Job title should be between 2 and 30 characters")
    private String jobTitle;

    @NotEmpty(message = "Must be gender")
    @Size(min = 2, max = 30, message = "Gender should be between 1 and 10 characters")
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.s")
    private LocalDate dateOfBirth;
}
