package com.ukrposhta.repository;

import com.ukrposhta.model.Role;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByRoleName(Role.RoleName roleName);

    Set<Role> getRolesByRoleNameIn(Set<Role.RoleName> roleName);
}
