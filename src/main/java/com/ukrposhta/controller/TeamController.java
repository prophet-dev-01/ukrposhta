package com.ukrposhta.controller;

import com.ukrposhta.mapper.TeamMapper;
import com.ukrposhta.model.Team;
import com.ukrposhta.model.dto.request.TeamRequestDto;
import com.ukrposhta.model.dto.response.TeamResponseDto;
import com.ukrposhta.service.TeamService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @PostMapping
    public TeamResponseDto create(@RequestBody TeamRequestDto requestDto) {
        return teamMapper.toDto(teamService.save(teamMapper.toEntity(requestDto)));
    }

    @GetMapping
    public List<TeamResponseDto> findAll(@RequestParam(defaultValue = "20") Integer count,
                                         @RequestParam(defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return teamService.findAll(pageRequest)
                .stream()
                .map(teamMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{id}/employee")
    public TeamResponseDto addEmployee(@PathVariable Long id,
                                                   @RequestParam Long employeeId) {
        return teamMapper.toDto(teamService.addEmployee(id, employeeId));
    }

    @PatchMapping("/{id}/project")
    public TeamResponseDto addProject(@PathVariable Long id,
                                      @RequestParam Long projectId) {
        return teamMapper.toDto(teamService.addProject(id, projectId));
    }

    @GetMapping("/{id}")
    public TeamResponseDto findById(@PathVariable Long id) {
        return teamMapper.toDto(teamService.findById(id));
    }

    @PostMapping("/{id}")
    public TeamResponseDto update(@PathVariable Long id,
                                  @RequestBody TeamRequestDto requestDto) {
        Team team = teamMapper.toEntity(requestDto);
        team.setId(id);
        return teamMapper.toDto(teamService.save(team));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.deleteById(id);
    }
}
