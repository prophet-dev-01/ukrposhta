package com.ukrposhta.service.impl;

import com.ukrposhta.exception.EmployeeNotFoundException;
import com.ukrposhta.model.Employee;
import com.ukrposhta.model.Project;
import com.ukrposhta.model.Team;
import com.ukrposhta.repository.EmployeeRepository;
import com.ukrposhta.repository.ProjectRepository;
import com.ukrposhta.repository.TeamRepository;
import com.ukrposhta.service.TeamService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public List<Team> findAllByIdIn(List<Long> teamIds) {
        return teamRepository.findAllByIdIn(teamIds);
    }

    @Override
    public Team findById(Long id) {
        return getTeamById(id);
    }

    @Override
    public Team addEmployee(Long id, Long employeeId) {
        Team team = getTeamById(id);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(
                "Couldn't find employee by id: " + id));
        if (team.getEmployees() == null) {
            team.setEmployees(new ArrayList<>());
        }
        team.getEmployees().add(employee);
        return teamRepository.save(team);
    }

    @Override
    public Team addProject(Long id, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Couldn't find project by id: " + projectId));
        Team team = getTeamById(id);
        if (team.getProjects() == null) {
            team.setProjects(new ArrayList<>());
        }
        team.getProjects().add(project);
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    private Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        "Couldn't find team by id: " + id));
    }
}
