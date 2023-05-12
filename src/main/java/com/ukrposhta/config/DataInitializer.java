package com.ukrposhta.config;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.service.AuthenticationService;
import com.ukrposhta.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    @PostConstruct
    public void inject() {
        Set<Role.RoleName> roleNames = Set.of(Role.RoleName.values());
        roleNames.forEach(
                (roleName) -> {
                    roleService.create(new Role(roleName));
                }
        );
        EmployeeRequestDto employee = new EmployeeRequestDto();
        employee.setLevel(Employee.Level.SENIOR);
        employee.setEmail("user@gmail.com");
        employee.setPassword("123456789");
        employee.setFirstName("Kolia");
        employee.setLastName("employeer");
        employee.setRoleNames(Set.of(Role.RoleName.QA, Role.RoleName.ADMIN));
        authenticationService.register(employee);
    }
}
