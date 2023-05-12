package com.ukrposhta.service.impl;

import com.ukrposhta.mapper.EmployeeMapper;
import com.ukrposhta.model.Employee;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.service.AuthenticationService;
import com.ukrposhta.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    @Override
    public Employee register(EmployeeRequestDto requestDto) {
        Employee employee = mapper.toEntity(requestDto);
        return employeeService.save(employee);
    }
}
