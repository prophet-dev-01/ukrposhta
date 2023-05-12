package com.ukrposhta.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.ukrposhta.model.Employee;
import com.ukrposhta.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserBuilder builder
                = withUsername(username);
        builder.password(employee.getPassword());
        builder.roles(employee.getRoles()
                .stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
