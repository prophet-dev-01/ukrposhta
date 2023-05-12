package com.ukrposhta.mapper;

import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Task;
import com.ukrposhta.model.Team;
import com.ukrposhta.model.dto.request.TeamRequestDto;
import com.ukrposhta.model.dto.response.TeamResponseDto;
import com.ukrposhta.service.EmployeeService;
import com.ukrposhta.service.ProjectService;
import com.ukrposhta.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {TaskService.class, ProjectService.class, EmployeeService.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        disableSubMappingMethodsGeneration = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeamMapper {
    @Mapping(target = "employeeIds", source = "employees", qualifiedByName = "toEmployeeIds")
    @Mapping(target = "taskIds", source = "tasks", qualifiedByName = "toTaskIds")
    @Mapping(target = "projectIds", source = "projects", qualifiedByName = "toProjectIds")
    TeamResponseDto toDto(Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", source = "employeeIds")
    @Mapping(target = "tasks", source = "taskIds")
    @Mapping(target = "projects", source = "projectIds")
    Team toEntity(TeamRequestDto teamRequestDto);

    @Named("toEmployeeIds")
    default List<Long> toEmployeeIds(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    @Named("toTaskIds")
    default List<Long> toTaskIds(List<Task> tasks) {
        return tasks.stream()
                .map(Task::getId)
                .collect(Collectors.toList());
    }

    @Named("toProjectIds")
    default List<Long> toProjectIds(List<Project> projects) {
        return projects.stream()
                .map(Project::getId)
                .collect(Collectors.toList());
    }
}
