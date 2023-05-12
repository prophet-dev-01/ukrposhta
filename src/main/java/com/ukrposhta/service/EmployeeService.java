package com.ukrposhta.service;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface EmployeeService {
    Employee save(Employee employee);

    Employee update(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    List<Employee> findAll(PageRequest pageRequest);

    List<Employee> findAllByIdIn(List<Long> employeeIds);

    void addRole(Long id, Role.RoleName roleName);

    void deleteRole(Long id, Role.RoleName roleName);

    Employee findByEmail(String email);

    void deleteById(Long id);
}
