package com.ukrposhta.controller;

import com.ukrposhta.mapper.EmployeeMapper;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.model.dto.response.EmployeeResponseDto;
import com.ukrposhta.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final EmployeeMapper mapper;

    @ApiOperation(value = "registration new employee")
    @PostMapping("/register")
    public EmployeeResponseDto register(@RequestBody @Valid EmployeeRequestDto requestDto) {
        return mapper.toDto(authenticationService.register(requestDto));
    }
}
