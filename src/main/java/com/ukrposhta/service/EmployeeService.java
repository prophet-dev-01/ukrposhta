package com.ukrposhta.service;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    List<Employee> findAllByIdIn(List<Long> employeeIds);

    void addRole(Long id, Role.RoleName roleName);

    void deleteRole(Long id, Role.RoleName roleName);

    Employee findByEmail(String email);

    void deleteById(Long id);
}
