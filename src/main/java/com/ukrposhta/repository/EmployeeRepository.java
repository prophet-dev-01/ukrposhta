package com.ukrposhta.repository;

import com.ukrposhta.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Transactional
    Optional<Employee> findByEmail(String email);
}
