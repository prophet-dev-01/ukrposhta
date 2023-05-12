package com.ukrposhta.service.impl;

import com.ukrposhta.model.Role;
import com.ukrposhta.repository.RoleRepository;
import com.ukrposhta.service.RoleService;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(Role.RoleName.valueOf(roleName))
                .orElseThrow(() -> new NoSuchElementException(
                        "Couldn't find role by name: " + roleName));
    }

    @Override
    public Set<Role> getByRoleNamesIn(Set<Role.RoleName> roleNames) {
        return roleRepository.getRolesByRoleNameIn(roleNames);
    }

    @Override
    public void deleteByRoleName(Role.RoleName roleName) {
        roleRepository.getRoleByRoleName(roleName)
                .orElseThrow(() -> new NoSuchElementException(
                        "Couldn't find role by name: " + roleName.name()));
    }
}
