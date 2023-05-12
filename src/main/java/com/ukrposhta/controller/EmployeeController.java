package com.ukrposhta.controller;

import com.ukrposhta.mapper.EmployeeMapper;
import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.model.dto.response.EmployeeResponseDto;
import com.ukrposhta.service.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PutMapping ("/{id}/role")
    public void addRole(@PathVariable Long id,
                        @RequestParam Role.RoleName roleName) {
        employeeService.addRole(id, roleName);
    }

    @GetMapping
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeService.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-email")
    public EmployeeResponseDto findByEmail(@RequestParam String email) {
        return employeeMapper.toDto(employeeService.findByEmail(email));
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto update(@PathVariable Long id,
                                      @Valid @RequestBody EmployeeRequestDto requestDto) {
        Employee employee = employeeMapper.toEntity(requestDto);
        employee.setId(id);
        return employeeMapper.toDto(employeeService.update(employee));
    }

    @DeleteMapping("/{id}/role")
    public void deleteRole(@PathVariable Long id,
                           @RequestParam Role.RoleName roleName) {
        employeeService.deleteRole(id, roleName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
