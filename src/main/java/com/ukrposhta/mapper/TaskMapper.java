package com.ukrposhta.mapper;

import com.ukrposhta.model.Task;
import com.ukrposhta.model.dto.request.TaskRequestDto;
import com.ukrposhta.model.dto.response.TaskResponseDto;
import com.ukrposhta.service.EmployeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {EmployeeService.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        disableSubMappingMethodsGeneration = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignedEmployee", source = "assignedEmployeeId")
    Task toEntity(TaskRequestDto requestDto);

    @Mapping(target = "assignedEmployeeId", source = "assignedEmployee.id")
    TaskResponseDto toDto(Task task);
}
