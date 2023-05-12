package com.ukrposhta.controller;

import com.ukrposhta.mapper.EmployeeMapper;
import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.model.dto.response.EmployeeResponseDto;
import com.ukrposhta.service.EmployeeService;
import com.ukrposhta.util.PageRequestUtil;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final PageRequestUtil pageRequestUtil;

    @PutMapping ("/{id}/role")
    @ApiOperation(value = "add a new employee")
    public void addRole(@PathVariable Long id,
                        @RequestParam Role.RoleName roleName) {
        employeeService.addRole(id, roleName);
    }

    @GetMapping
    @ApiOperation(value = "get employees list")
    public List<EmployeeResponseDto> getAllEmployees(
            @RequestParam(defaultValue = "20") Integer count,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest pageRequest = pageRequestUtil
                .getPageRequest(count, page, sortBy, "firstName", "level", "email");
        return employeeService.findAll(pageRequest)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-email")
    @ApiOperation(value = "find employee by email")
    public EmployeeResponseDto findByEmail(@RequestParam String email) {
        return employeeMapper.toDto(employeeService.findByEmail(email));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update employee")
    public EmployeeResponseDto update(@PathVariable Long id,
                                      @Valid @RequestBody EmployeeRequestDto requestDto) {
        Employee employee = employeeMapper.toEntity(requestDto);
        employee.setId(id);
        return employeeMapper.toDto(employeeService.update(employee));
    }

    @DeleteMapping("/{id}/role")
    @ApiOperation(value = "delete role from employee")
    public void deleteRole(@PathVariable Long id,
                           @RequestParam Role.RoleName roleName) {
        employeeService.deleteRole(id, roleName);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete employee")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
