package com.ukrposhta.service.impl;

import com.ukrposhta.exception.EmployeeNotFoundException;
import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.repository.EmployeeRepository;
import com.ukrposhta.repository.RoleRepository;
import com.ukrposhta.service.EmployeeService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Employee save(Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        employee.setPassword(encoder.encode(employee.getPassword()));
        Employee employeeFromDb = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Couldn't find employee by id: " + employee.getId()));
        if (employeeFromDb.getRegistrationDate() != null) {
            employee.setRegistrationDate(employeeFromDb.getRegistrationDate());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Couldn't find employee by id: " + id));
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(
                () -> new EmployeeNotFoundException("Couldn't find employee by email: " + email));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAll(PageRequest pageRequest) {
        return employeeRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Employee> findAllByIdIn(List<Long> employeeIds) {
        return employeeRepository.findAllById(employeeIds);
    }

    @Override
    public void addRole(Long id, Role.RoleName roleName) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(
                        "Couldn't find employee by id: " + id));
        employee.getRoles().add(roleRepository.getRoleByRoleName(roleName)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Couldn't find role by name: " + roleName)));
        employeeRepository.save(employee);
    }

    @Override
    public void deleteRole(Long id, Role.RoleName roleName) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(
                        "Couldn't find employee by id: " + id));
        employee.getRoles().remove(
                roleRepository.getRoleByRoleName(roleName)
                        .orElseThrow(() -> new NoSuchElementException(
                                "Couldn't find role by name: " + roleName)));
        employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
