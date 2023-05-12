package com.ukrposhta.model.dto.response;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdayDate;
    private LocalDate registrationDate;
    private Employee.Level level;
    private Set<Role.RoleName> roleNames;
}
