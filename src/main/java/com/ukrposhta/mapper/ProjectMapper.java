package com.ukrposhta.mapper;

import com.ukrposhta.model.Project;
import com.ukrposhta.model.Team;
import com.ukrposhta.model.dto.request.ProjectRequestDto;
import com.ukrposhta.model.dto.response.ProjectResponseDto;
import com.ukrposhta.service.TeamService;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {TeamService.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        typeConversionPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        disableSubMappingMethodsGeneration = true,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teams", source = "teamIds")
    Project toEntity(ProjectRequestDto requestDto);

    @Mapping(target = "teamIds", source = "teams", qualifiedByName = "toTeamIds")
    ProjectResponseDto toDto(Project project);

    @Named("toTeamIds")
    default Set<Long> toTeamIds(Set<Team> teams) {
        return teams.stream()
                .map(Team::getId)
                .collect(Collectors.toSet());
    }
}
