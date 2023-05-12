package com.ukrposhta.service;

import com.ukrposhta.model.Role;
import java.util.Set;

public interface RoleService {
    Role create(Role role);

    Role getByRoleName(String roleName);

    Set<Role> getByRoleNamesIn(Set<Role.RoleName> roleNames);

    void deleteByRoleName(Role.RoleName roleName);
}
