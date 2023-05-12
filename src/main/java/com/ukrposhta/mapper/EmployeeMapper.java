package com.ukrposhta.mapper;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Role;
import com.ukrposhta.model.dto.request.EmployeeRequestDto;
import com.ukrposhta.model.dto.response.EmployeeResponseDto;
import com.ukrposhta.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(uses = RoleService.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        disableSubMappingMethodsGeneration = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@Component
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "roles", source = "roleNames")
    Employee toEntity(EmployeeRequestDto requestDto);

    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "toRoleNames")
    EmployeeResponseDto toDto(Employee employee);

    @Named("toRoleNames")
    default Set<Role.RoleName> toRoleNames(Set<Role> roles) {
        return roles
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
    }
}
