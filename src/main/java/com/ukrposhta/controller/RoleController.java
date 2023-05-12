package com.ukrposhta.controller;

import com.ukrposhta.model.Role;
import com.ukrposhta.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public HttpStatus create(@RequestParam Role.RoleName roleName) {
        roleService.create(new Role(roleName));
        return HttpStatus.CREATED;
    }

    @DeleteMapping
    public HttpStatus delete(@RequestParam Role.RoleName roleName) {
        roleService.deleteByRoleName(roleName);
        return HttpStatus.ACCEPTED;
    }
}
