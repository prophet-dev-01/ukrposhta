package com.ukrposhta.controller;

import com.ukrposhta.mapper.ProjectMapper;
import com.ukrposhta.mapper.TeamMapper;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Status;
import com.ukrposhta.model.Team;
import com.ukrposhta.model.dto.request.ProjectRequestDto;
import com.ukrposhta.model.dto.response.ProjectResponseDto;
import com.ukrposhta.model.dto.response.TeamResponseDto;
import com.ukrposhta.service.ProjectService;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final TeamMapper teamMapper;

    @PostMapping
    public ProjectResponseDto create(@RequestBody @Valid ProjectRequestDto requestDto) {
        return projectMapper.toDto(projectService
                .save(projectMapper.toEntity(requestDto)));
    }

    @GetMapping("/{id}")
    public ProjectResponseDto findById(@PathVariable Long id) {
        return projectMapper.toDto(projectService.findById(id));
    }

    @GetMapping("{id}/teams")
    public Set<TeamResponseDto> getAllTeams(@PathVariable Long id) {
        Set<Team> teams = projectService.findById(id).getTeams();
        return teams.stream().map(teamMapper::toDto).collect(Collectors.toSet());
    }

    @PutMapping("/{id}")
    public ProjectResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid ProjectRequestDto requestDto) {
        Project project = projectMapper.toEntity(requestDto);
        project.setId(id);
        return projectMapper.toDto(projectService.save(project));
    }

    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestParam Status status) {
        projectService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
