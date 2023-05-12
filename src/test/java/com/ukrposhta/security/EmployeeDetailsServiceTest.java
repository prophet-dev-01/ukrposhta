package com.ukrposhta.security;

import static org.mockito.ArgumentMatchers.any;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class EmployeeDetailsServiceTest {
    private static Employee defaultEmployee;
    private static Role defaultRole;
    private EmployeeDetailsService detailsService;
    private EmployeeRepository employeeRepository;

    @BeforeAll
    static void init() {
        defaultRole = new Role();
        defaultRole.setId(1L);
        defaultRole.setRoleName(Role.RoleName.QA);
        defaultEmployee = new Employee();
        defaultEmployee.setId(1L);
        defaultEmployee.setRegistrationDate(LocalDate.now());
        defaultEmployee.setLevel(Employee.Level.JUNIOR);
        defaultEmployee.setFirstName("User");
        defaultEmployee.setLastName("UserLastName");
        defaultEmployee.setEmail("default@gmail.com");
        defaultEmployee.setPassword("123456");
        defaultEmployee.setRoles(Set.of(defaultRole));
    }

    @BeforeEach
    void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        detailsService = new EmployeeDetailsService(employeeRepository);
    }

    @Test
    void loadUserByUserName_validCase_ok() {
        Mockito.when(employeeRepository.findByEmail(any()))
                .thenReturn((Optional.ofNullable(defaultEmployee)));
        UserDetails result = detailsService
                .loadUserByUsername(defaultEmployee.getEmail());
        Assertions.assertEquals(
                defaultEmployee.getEmail(), result.getUsername(),
                "The user's email address must be found");
        Assertions.assertEquals(result.getAuthorities().size(), defaultEmployee.getRoles().size());
    }

    @Test
    void loadUserByUserName_nullEmail_notOk() {
        Mockito.when(employeeRepository.findByEmail(
                        defaultEmployee.getEmail()))
                .thenReturn(Optional.ofNullable(defaultEmployee));
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> detailsService.loadUserByUsername(null),
                "User with a null email must throw exception");
    }

    @Test
    void loadUserByUserName_incorrectEmail_notOk() {
        Mockito.when(employeeRepository.findByEmail(
                defaultEmployee.getEmail())).thenReturn(Optional.ofNullable(defaultEmployee));
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> detailsService.loadUserByUsername("invalid@gmail.com"),
                "User with a incorrect email must throw exception");
    }
}
