package com.ukrposhta.model.dto.request;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.validation.ValidEmail;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ValidEmail
    private String email;
    @Size(min = 6, max = 15)
    private String password;
    private LocalDate birthdayDate;
    private Employee.Level level;
    @NotNull
    private Set<Role.RoleName> roleNames;

    public enum Level {
        JUNIOR,
        MIDDLE,
        SENIOR
    }
}
