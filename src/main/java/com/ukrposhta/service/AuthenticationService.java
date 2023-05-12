package com.ukrposhta.service;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;

public interface AuthenticationService {
    Employee register(EmployeeRequestDto requestDto);
}
